import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlin.random.Random

object GenerateTruck {


    val channels = mutableListOf<Channel<Truck>>()
    private val Leadingscope = CoroutineScope(Job() + Dispatchers.Default)


    val waiting = mutableListOf<Truck>()


    private fun fill(truck: Truck): Truck {
        val choseType = Random.nextInt(1, 3)
        when (choseType) {
            1 -> for (i in 0..Random.nextInt(0, truck.capacity)) {

                when (Random.nextInt(1, 4)) {
                    1 -> if (truck.cargo + Products.milk.weight > truck.capacity) {
                        break
                    } else
                        truck.milk++

                    2 -> if (truck.cargo + Products.potato.weight > truck.capacity) {
                        break
                    } else
                        truck.potato++

                    3 -> if (truck.cargo + Products.bread.weight > truck.capacity) {
                        break
                    } else
                        truck.bread++


                }

            }
            2 -> for (i in 0..Random.nextInt(0, truck.capacity)) {

                when (Random.nextInt(1, 10)) {
                    1 -> if (truck.cargo + Oversize.bath.weight > truck.capacity) {
                        break
                    } else truck.bath++
                    2 -> if (truck.cargo + Oversize.locker.weight > truck.capacity) {
                        break
                    } else truck.locker++
                    3 -> if (truck.cargo + Oversize.bed.weight > truck.capacity) {
                        break
                    } else truck.bed++
                    4 -> if (truck.cargo + Mediumsize.tv.weight > truck.capacity) {
                        break
                    } else truck.tv++
                    5 -> if (truck.cargo + Mediumsize.pc.weight > truck.capacity) {
                        break
                    } else truck.pc++
                    6 -> if (truck.cargo + Mediumsize.chair.weight > truck.capacity) {
                        break
                    } else truck.chair++
                    7 -> if (truck.cargo + Smallsize.phone.weight > truck.capacity) {
                        break
                    } else truck.phone++
                    8 -> if (truck.cargo + Smallsize.lamp.weight > truck.capacity) {
                        break
                    } else truck.lamp++
                    9 -> if (truck.cargo + Smallsize.scissors.weight > truck.capacity) {
                        break
                    } else truck.scissors++

                }
            }


        }
        return truck
    }

    fun addChannel() {
        repeat(3) {
            channels.add(Channel<Truck>())
        }
    }


    init {


        Leadingscope.launch {


            repeat(500) {
                var helpCount = Random.nextInt(1, 4)

                when (helpCount) {
                    1 -> waiting.add(fill(LightTruck()))
                    2 -> waiting.add(fill(MediumTrack()))
                    3 -> waiting.add(fill(HeavyTruck()))
                }
                delay(30000)


            }


        }

        Leadingscope.launch {

            delay(60000)
            repeat(500) {

                if (waiting.size > 2) {
                    channels[0].send(waiting[0])

                    waiting.removeAt(0)
                } else delay(10000)

            }
        }
        Leadingscope.launch {

            delay(60000)
            repeat(500) {

                if (waiting.size > 4) {
                    channels[1].send(waiting[1])

                    waiting.removeAt(1)
                } else delay(10000)


            }


            Leadingscope.launch {

                delay(60000)
                repeat(500) {

                    if (waiting.size > 5) {
                        channels[2].send(waiting[2])

                        waiting.removeAt(2)
                    } else delay(10000)


                }

            }


        }


    }
}








