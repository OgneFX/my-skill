import kotlinx.coroutines.*
import kotlinx.coroutines.flow.toSet

class Gamer(var name: String) {
    var getCard = mutableListOf<Int>()


    suspend fun getCard() = coroutineScope {

        val job = launch {
            val inputList: MutableSet<Int> = Leading.flow.toSet() as MutableSet<Int>
            for (i in 0..14) {
                getCard.add(inputList.elementAt(i))
                getCard.sort()
            }

        }
        job.join()
    }

}









