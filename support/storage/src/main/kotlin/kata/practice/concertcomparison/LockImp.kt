package kata.practice.concertcomparison

import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

interface Lock{
    fun lock(lockName: String)
    fun multiLock(lockNames: List<String>)
    fun unlock(lockName: String)
    fun multiUnLock(lockNames: List<String>)
}


@Component
class LockImp(
    private val redissonClient: RedissonClient
): Lock {

    private val log = LoggerFactory.getLogger(javaClass)


    override fun lock(lockName: String) {
        val rLock = rLock(lockName)

        try {
            if (rLock.tryLock(WAIT_TIME, LESS_TIME, TIME_UNIT)) {
                return
            }

            throw IllegalStateException("Unable to acquire lock")
        }catch (e: InterruptedException) {
            throw InterruptedException()
        }
    }

    override fun multiLock(lockNames: List<String>) {
        val rMultiLock = rMultiLock(lockNames)

        try {
            if (rMultiLock.tryLock(WAIT_TIME, LESS_TIME, TIME_UNIT)) {
                return
            }

            throw IllegalStateException("Unable to acquire lock")
        }catch (e: InterruptedException) {
            throw InterruptedException()
        }
    }



    override fun unlock(lockName: String) {
        try {
            val admissionLock = rLock(lockName)
            if (admissionLock.isHeldByCurrentThread) {
                admissionLock.unlock()
                return
            }

            throw IllegalStateException("AdmissionLock is not held by current thread")
        } catch (e: Exception) {
            //IllegalMonitorStateException
            log.error("Redisson Lock Already Unlocked {}", lockName, e)
        }
    }

    override fun multiUnLock(lockNames: List<String>){
        lockNames.forEach { lockName ->
            unlock(lockName)
        }
    }


    private fun rLock(ticketId: String): RLock {
        return redissonClient.getLock(ticketId)
    }

    private fun rMultiLock(lockNames: List<String>): RLock {
        val lock = lockNames
            .map(redissonClient::getLock)

        return redissonClient.getMultiLock(*lock.toTypedArray())
    }


    companion object{
        private const val WAIT_TIME = 5L
        private const val LESS_TIME = 3L
        private val TIME_UNIT = TimeUnit.SECONDS
    }


}