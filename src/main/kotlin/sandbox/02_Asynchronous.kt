package sandbox

/**
 * @author Adriano Belfort (adriano.belfort@playkids.com) on 2019-03-10
 */

object Asynchronous {
    fun getPageText(url: String, callback: (String) -> Unit) {
        networkCall(url) { response ->
            callback(response.body)
        }
    }

    fun networkCall(url: String, callback: (Response) -> Unit) {
        println("Fetching $url")

        Thread.sleep(2000)      // Blocks thread

        callback(Response(200, url.split('.')[1]))
    }
}

fun Asynchronous.nestedHierarchy() {
    getPageText("www.google.com") { google ->
        getPageText("www.microsoft.com") { microsoft ->
            getPageText("www.movile.com") { movile ->
                getPageText("www.playkids.com") { playkids ->
                    getPageText("www.amazon.com") { amazon ->
                        println("Pages: $google, $microsoft, $movile, $playkids, $amazon")
                    }
                }
            }
        }
    }
}

fun main() {
    /*getPageText("www.google.com") { google ->
        println(google)
    }*/

    Asynchronous.nestedHierarchy()
}