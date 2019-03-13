package presentation

import kotlinx.coroutines.*

/**
 * @author Adriano Belfort (adriano.belfort@playkids.com) on 2019-03-13
 */

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
        while(isActive) {
            val song = songs[i++ % songs.size]
            println("🎧 ${Thread.currentThread().name} Now playing: $song")
            delay(5000)
        }
    }
}