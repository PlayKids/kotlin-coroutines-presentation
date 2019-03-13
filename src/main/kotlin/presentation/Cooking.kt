package presentation

import kotlinx.coroutines.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.random.Random

/**
 * @author Adriano Belfort (adriano.belfort@playkids.com) on 2019-03-13
 */

typealias Ingredient = String
typealias CookedIngredient = String
typealias Food = String

suspend fun prepareIngredient(ingredient: Ingredient, cookingTime: Long): CookedIngredient {
    println("🍛 I'll prepare $ingredient now (takes $cookingTime seconds) (Chef ${Thread.currentThread().name})")
    delay(cookingTime * 1000)
    return "cooked $ingredient"
}

fun assemble(name: String, vararg cookedIngredient: CookedIngredient): Food {
    return "$name (contains ${cookedIngredient.joinToString(", ")})"
}

fun eat(food: Food) {
    println("😋 I'm eating a delicious $food")
}

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

fun CoroutineScope.cook(dispatcher: CoroutineDispatcher): Job {
    return launch(dispatcher) {
        val plateName = "beef with fried eggs"

        val startTime = System.currentTimeMillis()

        val rice = prepareIngredient("rice", 8)
        val beans = prepareIngredient("beans", 15)
        val beef = prepareIngredient("beef", 10)
        val eggs = prepareIngredient("eggs", 4)

        val plate = assemble(plateName, rice, beans, beef, eggs)

        val endTime = System.currentTimeMillis()

        val seconds = (endTime - startTime) / 1000

        println("😋 After $seconds seconds my $plateName is ready!")

        eat(plate)
    }
}

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

fun CoroutineScope.drinkWater(dispatcher: CoroutineDispatcher): Job {
    return launch(dispatcher) {
        while(coroutineContext.isActive) {
            println("💧 Time to drink water! (${Thread.currentThread().name})")
            delay(5000)
        }
    }
}

fun CoroutineScope.playMusic(dispatcher: CoroutineDispatcher): Job {
    return launch(dispatcher) {
        val songs = listOf(
            "Queen - Somebody To Love",
            "Kevin o Chris - Medley da Gaiola (Dennis DJ Remix)",
            "Major Lazer - Believer",
            "Ariana Grande - 7 rings",
            "Anitta - Veneno"
        )

        var i = 0
        while(coroutineContext.isActive) {
            val song = songs[i++ % songs.size]
            println("🎧 ${Thread.currentThread().name} Now playing: $song")
            delay(5000)
        }
    }
}
