import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

object Leading {

    private val Leadingscope = CoroutineScope(Job() + Dispatchers.Default)
    private val _sharedFlow = MutableSharedFlow<Int>()
    val sharedflow = _sharedFlow.asSharedFlow()
    var barrel = mutableListOf<Int>()

    val flow = flow {

        for (i in 0..90) {
            emit(Random.nextInt(1, 90))

        }
    }

    init {
        for (i in 1..90) {
            barrel.add(i)
        }
        barrel.shuffle()
        println(barrel)
        Leadingscope.launch {

            repeat(10000) {

                _sharedFlow.emit(barrel.elementAt(it))
                delay(500)
            }

        }
    }


}