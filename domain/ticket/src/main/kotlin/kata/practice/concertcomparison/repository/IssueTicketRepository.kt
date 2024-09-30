package kata.practice.concertcomparison.repository

import kata.practice.concertcomparison.model.IssueTicket
import org.springframework.data.jpa.repository.JpaRepository

interface IssueTicketRepository: JpaRepository<IssueTicket, Long>