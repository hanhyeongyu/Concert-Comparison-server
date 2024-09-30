package kata.practice.concertcomparison.application

import kata.practice.concertcomparison.Lock
import kata.practice.concertcomparison.application.command.AddTickets
import kata.practice.concertcomparison.application.command.IssueTickets
import kata.practice.concertcomparison.application.command.SelectTickets
import kata.practice.concertcomparison.common.exception.EntityNotFoundException
import kata.practice.concertcomparison.model.IssueTicket
import kata.practice.concertcomparison.model.Ticket
import kata.practice.concertcomparison.repository.*
import kata.practice.concertcomparison.service.TimerService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionTemplate
import kotlin.jvm.optionals.getOrNull


@Service
class TicketApplication(
    private val transactionManager: PlatformTransactionManager,
    private val lock: Lock,
    private val ticketRepository: TicketRepository,
    private val issueTicketRepository: IssueTicketRepository,
    private val timerService: TimerService,
    private val performanceRepository: PerformanceRepository,
    private val mapsRepository: MapsRepository,
    private val seatsRepository: SeatRepository,
){

    private val log = LoggerFactory.getLogger(javaClass)
    private val transactionTemplate = TransactionTemplate(transactionManager)

    @Transactional
    fun handle(command: AddTickets){
        val performance = performanceRepository.findById(command.performanceId)
            .getOrNull() ?: throw EntityNotFoundException()

        val maps = mapsRepository.findById(command.mapId)
            .getOrNull() ?: throw EntityNotFoundException()

        val seats = seatsRepository.findAllByMapId(maps.id!!)

        val tickets = seats.map { seat ->
            Ticket(
                performanceId = performance.id!!,
                mapId = maps.id!!,
                seatId = seat.id!!
            )
        }
        ticketRepository.saveAll(tickets)
    }

    fun handle(command: SelectTickets){
        val userId = command.userId
        val ticketIds = command.ticketIds

        try{
            lock.multiLock(lockNames(ticketIds))
            transactionTemplate.execute {
                val tickets = ticketRepository.findAllById(ticketIds)
                for (ticket in tickets){
                    ticket.select(userId)
                }

                ticketRepository.saveAll(tickets)

                log.info("티켓을 선택하였습니다.")
                startTimeOut(userId, ticketIds)
            }
        }finally {
            lock.multiUnLock(lockNames(ticketIds))
        }
    }


    fun handle(command: IssueTickets){
        val ticketIds = command.ticketIds
        val userId = command.userId

        try{
            lock.multiLock(lockNames(ticketIds))
            transactionTemplate.execute {

                val tickets = ticketRepository.findAllById(ticketIds)

                val newIssueTickets = mutableListOf<IssueTicket>()
                for (ticket in tickets){
                    val newIssueTicket = ticket.issue(userId)
                    newIssueTickets.add(newIssueTicket)
                }

                ticketRepository.saveAll(tickets)
                issueTicketRepository.saveAll(newIssueTickets)

                cancelTimeOut(userId)
            }
        }finally {
            lock.multiUnLock(lockNames(ticketIds))
        }
    }


    private fun startTimeOut(userId: Long, ticketIds: List<Long>){
        timerService.startTimer(userId){
            try {
                lock.multiLock(lockNames(ticketIds))
                transactionTemplate.execute {
                    val tickets = ticketRepository.findAllById(ticketIds)
                    for (ticket in tickets) {
                        ticket.timeOut()
                    }
                    ticketRepository.saveAll(tickets)
                }
            }finally{
                lock.multiUnLock(lockNames(ticketIds))
            }
        }
    }

    private fun cancelTimeOut(userId: Long){
        timerService.cancelTimer(userId)
    }

    private fun lockName(ticketId: Long): String{
        return ticketId.toString()
    }
    private fun lockNames(ticketIds: List<Long>): List<String>{
        return ticketIds.map(::lockName)
    }

}