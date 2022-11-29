import kotlinx.coroutines.*

object Game {


    suspend fun woho() = coroutineScope {
        println("Give name player 1")
        val firstPlayer = Gamer(readln())
        println("Give name player 2")
        val secondPlayer = Gamer(readln())


        val job = launch {

            firstPlayer.getCard()
            secondPlayer.getCard()
            val a = firstPlayer.getCard
            val b = secondPlayer.getCard


            Leading.sharedflow.collect()
            {
                val y = it
                a.removeIf { it == y }
                b.removeIf { it == y }
                println(it)
                println(firstPlayer.name + ": $a")
                println(secondPlayer.name + ": $b")
                println()

                if (a.isEmpty()) {
                    println(firstPlayer.name + " WINNER")
                    cancel()
                }
                if (b.isEmpty()) {
                    println(secondPlayer.name + " WINNER")
                    cancel()
                }

            }
        }
        job.join()


    }
}
















