package presentation

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * @author Adriano Belfort (adriano.belfort@playkids.com) on 2019-03-13
 */

fun main() = runBlocking<Unit> {

    val person = Executors
        .newSingleThreadExecutor()      // That's me!
        .asCoroutineDispatcher()

    val cookingJob = cook(person)

    val messagingJob = interactWithFriends(person)

    val waterDrinkingJob = drinkWater(person)

    val jukebox = Executors
        .newSingleThreadExecutor()
        .asCoroutineDispatcher()

    val spotifyJob = playMusic(jukebox)

    cookingJob.join()
    messagingJob.cancelAndJoin()
    waterDrinkingJob.cancelAndJoin()
    spotifyJob.cancelAndJoin()

    (person.executor as ExecutorService).shutdown()
    (jukebox.executor as ExecutorService).shutdown()

}