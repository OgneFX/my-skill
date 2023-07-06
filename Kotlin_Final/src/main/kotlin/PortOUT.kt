import kotlinx.coroutines.delay

import kotlin.random.Random

class PortOUT {


    suspend fun outload(truck: Truck) {

        val choseType = Random.nextInt(1, 5)
        when (choseType) {
            1 -> while (truck.cargo != truck.capacity) {

                when (Random.nextInt(1, 4)) {
                    1 -> if (CargoMain.ProductList["bath"]!! >= 1)
                        if (truck.cargo + Oversize.bath.weight >= truck.capacity)
                            break
                        else {
                            truck.bath++
                            CargoMain.ProductList["bath"] = CargoMain.ProductList["bath"]!!-1
                            CargoMain.outputItems++
                            delay(Oversize.bath.timeToMove.toLong())
                        }
                    2 -> if (CargoMain.ProductList["locker"]!! >= 1)
                        if (truck.cargo + Oversize.locker.weight >= truck.capacity)
                            break
                        else {
                            truck.locker++
                            CargoMain.ProductList["locker"] = CargoMain.ProductList["locker"]!!-1
                            CargoMain.outputItems++
                            delay(Oversize.locker.timeToMove.toLong())
                        }
                    3 -> if (CargoMain.ProductList["bed"]!! >= 1)
                        if (truck.cargo + Oversize.bed.weight >= truck.capacity)
                            break
                        else {
                            truck.bed++
                            CargoMain.ProductList["bed"] = CargoMain.ProductList["bed"]!!-1
                            CargoMain.outputItems++
                            delay(Oversize.bed.timeToMove.toLong())
                        }
                }


            }
            2 -> while (truck.cargo != truck.capacity) {

                when (Random.nextInt(1, 4)) {
                    1 -> if (CargoMain.ProductList["tv"]!! >= 1)
                        if (truck.cargo + Mediumsize.tv.weight >= truck.capacity)
                            break
                        else {
                            truck.tv++
                            CargoMain.ProductList["tv"] = CargoMain.ProductList["tv"]!!-1
                            CargoMain.outputItems++
                            delay(Mediumsize.tv.timeToMove.toLong())
                        }
                    2 -> if (CargoMain.ProductList["pc"]!! >= 1)
                        if (truck.cargo + Mediumsize.pc.weight >= truck.capacity)
                            break
                        else {
                            truck.pc++
                            CargoMain.ProductList["pc"] = CargoMain.ProductList["pc"]!!-1
                            CargoMain.outputItems++
                            delay(Mediumsize.pc.timeToMove.toLong())
                        }
                    3 -> if (CargoMain.ProductList["chair"]!! >= 1)
                        if (truck.cargo + Mediumsize.chair.weight >= truck.capacity)
                            break
                        else {
                            truck.chair++
                            CargoMain.ProductList["chair"] = CargoMain.ProductList["chair"]!!-1
                            CargoMain.outputItems++
                            delay(Mediumsize.chair.timeToMove.toLong())
                        }
                }


            }
            3 -> while (truck.cargo != truck.capacity) {

                when (Random.nextInt(1, 4)) {
                    1 -> if (CargoMain.ProductList["phone"]!! >= 1)
                        if (truck.cargo + Smallsize.phone.weight >= truck.capacity)
                            break
                        else {
                            truck.phone++
                            CargoMain.ProductList["phone"] = CargoMain.ProductList["phone"]!!-1
                            CargoMain.outputItems++
                            delay(Smallsize.phone.timeToMove.toLong())
                        }
                    2 -> if (CargoMain.ProductList["lamp"]!! >= 1)
                        if (truck.cargo + Smallsize.lamp.weight >= truck.capacity)
                            break
                        else {
                            truck.lamp++
                            CargoMain.ProductList["lamp"] = CargoMain.ProductList["lamp"]!!-1
                            CargoMain.outputItems++
                            delay(Smallsize.lamp.timeToMove.toLong())
                        }
                    3 -> if (CargoMain.ProductList["scissors"]!! >= 1)
                        if (truck.cargo + Smallsize.scissors.weight >= truck.capacity)
                            break
                        else {
                            truck.scissors++
                            CargoMain.ProductList["scissors"] = CargoMain.ProductList["scissors"]!!-1
                            CargoMain.outputItems++
                            delay(Smallsize.scissors.timeToMove.toLong())
                        }
                }


            }
            4 -> while (truck.cargo != truck.capacity) {

                when (Random.nextInt(1, 4)) {
                    1 -> if (CargoMain.ProductList["milk"]!! >= 1)
                        if (truck.cargo + Products.milk.weight >= truck.capacity)
                            break
                        else {
                            truck.milk++
                            CargoMain.ProductList["milk"] = CargoMain.ProductList["milk"]!!-1
                            CargoMain.outputItems++
                            delay(Products.milk.timeToMove.toLong())
                        }
                    2 -> if (CargoMain.ProductList["potato"]!! >= 1)
                        if (truck.cargo + Products.potato.weight >= truck.capacity)
                            break
                        else {
                            truck.potato++
                            CargoMain.ProductList["potato"] = CargoMain.ProductList["potato"]!!-1
                            CargoMain.outputItems++
                            delay(Products.potato.timeToMove.toLong())
                        }
                    3 -> if (CargoMain.ProductList["bread"]!! >= 1)
                        if (truck.cargo + Products.bread.weight >= truck.capacity)
                            break
                        else {
                            truck.bread++
                            CargoMain.ProductList["bread"] = CargoMain.ProductList["bread"]!!-1
                            CargoMain.outputItems++
                            delay(Products.bread.timeToMove.toLong())
                        }
                }


            }

        }
    }
}