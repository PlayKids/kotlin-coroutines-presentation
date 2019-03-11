import CoroutinesAsync.nestedHierarchy
import kotlinx.coroutines.*

/**
 * @author Adriano Belfort (adriano.belfort@playkids.com) on 2019-03-10
 */

object CoroutinesAsync {
    suspend fun getPageText(url: String): String {
        val response = networkCall(url)
        return response.body
    }

    suspend fun networkCall(url: String): Response {
        println("Fetching $url")

        delay(2000)

        return Response(200, url.split('.')[1])
    }

    suspend fun nestedHierarchy() {
        coroutineScope {
            val google = async { getPageText("www.google.com") }
            val microsoft = async { getPageText("www.microsoft.com") }
            val movile = async { getPageText("www.movile.com") }
            val playkids = async { getPageText("www.playkids.com") }
            val amazon = async { getPageText("www.amazon.com") }

            println ("Pages: ${google.await()}, ${microsoft.await()}, ${movile.await()}, ${playkids.await()}, ${amazon.await()}")
        }
    }
}


fun main() = runBlocking {
    nestedHierarchy()
}