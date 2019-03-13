package presentation

import kotlinx.coroutines.*

/**
 * @author Adriano Belfort (adriano.belfort@playkids.com) on 2019-03-13
 */

fun CoroutineScope.drinkWater(dispatcher: CoroutineDispatcher): Job {
    return launch(dispatcher) {
        while(isActive) {
            println("💧 Time to drink water! (${Thread.currentThread().name})")
            delay(5000)
        }
    }
}