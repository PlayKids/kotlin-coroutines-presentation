import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @author Adriano Belfort (adriano.belfort@playkids.com) on 2019-03-10
 */

object Coroutines {
    suspend fun getPageText(url: String): String {
        val response = networkCall(url)
        return response.body
    }

    suspend fun networkCall(url: String): Response {
        println("Fetching $url")

        delay(2000)

        return Response(200, url.split('.')[1])
    }
}

suspend fun Coroutines.nestedHierarchy() {
    val google = getPageText("www.google.com")
    val microsoft = getPageText("www.microsoft.com")
    val movile = getPageText("www.movile.com")
    val playkids = getPageText("www.playkids.com")

    println("Pages: $google, $microsoft, $movile, $playkids")
}

fun main() = runBlocking {
    Coroutines.nestedHierarchy()
}