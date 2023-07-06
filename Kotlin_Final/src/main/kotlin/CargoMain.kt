import kotlinx.coroutines.*
import kotlin.random.Random


object CargoMain {

    var inputItems: Int = 0
    var outputItems: Int = 0

    val ProductList = mutableMapOf("bath" to 0, "locker" to 0, "bed" to 0, "tv" to 0, "pc" to 0, "chair" to 0, "phone" to 0,
        "lamp" to 0, "scissors" to 0, "milk" to 0, "potato" to 0, "bread" to 0)

    private val PortIN = mutableListOf<PortIN>()
    private val portOut = PortOUT()


    suspend fun woho() = coroutineScope {

        launch(Dispatchers.Default) {

            repeat(3) {
                PortIN.add(PortIN())
            }
            GenerateTruck.addChannel()

            for (i in 0..2) {

                launch(Dispatchers.Default) {

                    repeat(500) {

                        PortIN[i].reload(GenerateTruck.channels[i].receive())

                    }


                }
            }


        }




        launch(Dispatchers.Default) {
            delay(10000)
            var i: Truck
            repeat(500) {

                delay(70000)

                when (Random.nextInt(1, 3)) {
                    1 -> {
                        i = LightTruck()
                        portOut.outload(i)

                    }
                    2 -> {
                        i = MediumTrack()
                        portOut.outload(i)


                    }
                }


            }


        }
        launch {
            repeat(500) {
                delay(10000)
                for(i in ProductList){
                    println("${i.key} - ${i.value}")
                }

                println()
                println("Input items: $inputItems")
                println("Output items: $outputItems")
                println()
                println("Waiting: ${GenerateTruck.waiting.size}")
            }

        }


    }
}
