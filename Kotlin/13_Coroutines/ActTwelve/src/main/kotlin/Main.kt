import Fibonacci.take
import kotlinx.coroutines.*


suspend fun main(): Unit = coroutineScope {

    val jobDot = launch {

        for (i in 1..500) {
            delay(20)
            if (i % 100 == 0)
                println(".")
            else
                print(".")
        }

    }
    launch {


        take(15000000)

        take(15000)
        take(1500000)
        jobDot.cancel()
    }


}







