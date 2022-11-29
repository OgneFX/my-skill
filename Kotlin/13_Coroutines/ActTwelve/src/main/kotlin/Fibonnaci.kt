import kotlinx.coroutines.*


object Fibonacci {

    suspend fun take(numF: Int) {


        try {
            withTimeout(2000)
            {


                var f1 = 0.toBigInteger()
                var f2 = 1.toBigInteger()
                var i = 0



                while (i < numF) {

                    val summfib = f1 + f2
                    f1 = f2
                    f2 = summfib
                    i++
                    if (!isActive) {
                        yield()
                    }
                }
                println(f1)
                println()
            }
        } catch (e: TimeoutCancellationException) {
            println("Too many time to wait")
        }


    }
}







