package presentation

import kotlinx.coroutines.*
import kotlin.random.Random

/**
 * @author Adriano Belfort (adriano.belfort@playkids.com) on 2019-03-13
 */

fun CoroutineScope.interactWithFriends(dispatcher: CoroutineDispatcher): Job {
    return launch(dispatcher) {
        val friends = listOf("Alexandre", "Caique", "Júlio", "Carlos", "Fellipe")
        val replies = listOf("Awesome!", "ata", "OK", "What do you think about 5PM?", "Sure!", "I like that", "Cool!")

        while(coroutineContext.isActive) {
            val friend = friends[Random.nextInt(friends.size)]
            val reply = replies[Random.nextInt(replies.size)]

            println("📲 $friend sent me a message! Let me reply... (${Thread.currentThread().name})")
            delay(1500)
            println("📲 Sent message to $friend: '$reply' (${Thread.currentThread().name})")
            delay(Random.nextInt(10000).toLong())
        }
    }
}