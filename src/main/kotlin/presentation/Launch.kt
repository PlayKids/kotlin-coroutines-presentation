package presentation

import kotlinx.coroutines.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * @author Adriano Belfort (adriano.belfort@playkids.com) on 2019-03-13
 */

fun main() {
    val dispatcher = Executors.newFixedThreadPool(5).asCoroutineDispatcher()

    val job = GlobalScope.launch(dispatcher) {
        repeat(10) {
            println("Hello from ${Thread.currentThread().name}")
            delay(200)
        }
    }

    runBlocking { job.join() }

    (dispatcher.executor as ExecutorService).shutdown()
}