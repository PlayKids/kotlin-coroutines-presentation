package presentation

import kotlinx.coroutines.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * @author Adriano Belfort (adriano.belfort@playkids.com) on 2019-03-13
 */

fun main() = runBlocking<Unit> {

    val person = Executors
        .newSingleThreadExecutor()      // That's me!
        .asCoroutineDispatcher()

    val cooking = cook(person)

    val messaging = interactWithFriends(person)

    val waterDrinking = drinkWater(person)

    val jukebox = Executors
        .newSingleThreadExecutor()
        .asCoroutineDispatcher()

    val musicService = playMusic(jukebox)

    cooking.join()
    messaging.cancelAndJoin()
    waterDrinking.cancelAndJoin()
    musicService.cancelAndJoin()

    (person.executor as ExecutorService).shutdown()
    (jukebox.executor as ExecutorService).shutdown()

}