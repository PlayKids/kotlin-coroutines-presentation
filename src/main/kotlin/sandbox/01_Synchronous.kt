package sandbox

/**
 * @author Adriano Belfort (adriano.belfort@playkids.com) on 2019-03-10
 */

object Synchronous {
    fun getPageText(url: String): String {
        val response = networkCall(url)

        return response.body
    }

    fun networkCall(url: String): Response {
        println("Fetching $url")

        Thread.sleep(2000)      // Blocks thread

        return Response(200, url.split('.')[1])
    }

    fun getMany() {
        val google = getPageText("www.google.com")
        val microsoft = getPageText("www.microsoft.com")
        val movile = getPageText("www.movile.com")
        val playkids = getPageText("www.playkids.com")
        val amazon = getPageText("www.amazon.com")

        println("Pages: $google, $microsoft, $movile, $playkids, $amazon")
    }
}

fun main() {
    Synchronous.getMany()
}