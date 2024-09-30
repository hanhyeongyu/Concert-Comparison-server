package kata.practice.concertcomparison.service

import org.springframework.stereotype.Service
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit


@Service
class TimerService {

    private val scheduler = Executors.newScheduledThreadPool(1)
    private val timers = mutableMapOf<Long, ScheduledFuture<*>>()

    fun startTimer(
        timerId: Long,
        onTimeout: () -> Unit) {
        val future = scheduler.schedule({
            onTimeout()
            timers.remove(timerId)
        }, 10, TimeUnit.MINUTES)
        timers[timerId] = future
    }

    fun cancelTimer(timerId: Long) {
        timers[timerId]?.cancel(false)
        timers.remove(timerId)
    }
}