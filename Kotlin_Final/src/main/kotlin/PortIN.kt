import kotlinx.coroutines.delay

class PortIN {



    suspend fun reload (truck : Truck) {

        val timeToReload = truck.timeToMove/3

        delay(timeToReload.toLong())
        CargoMain.ProductList["bath"] = CargoMain.ProductList["bath"]!!+truck.bath
        CargoMain.ProductList["locker"] = CargoMain.ProductList["locker"]!!+truck.locker
        CargoMain.ProductList["bed"] = CargoMain.ProductList["bed"]!!+truck.bed
        CargoMain.ProductList["tv"] = CargoMain.ProductList["tv"]!!+truck.tv
        delay(timeToReload.toLong())
        CargoMain.ProductList["pc"] = CargoMain.ProductList["pc"]!!+truck.pc
        CargoMain.ProductList["chair"] = CargoMain.ProductList["chair"]!!+truck.chair
        CargoMain.ProductList["phone"] = CargoMain.ProductList["phone"]!!+truck.phone
        CargoMain.ProductList["lamp"] = CargoMain.ProductList["lamp"]!!+truck.lamp
        delay(timeToReload.toLong())
        CargoMain.ProductList["scissors"] = CargoMain.ProductList["scissors"]!!+truck.scissors
        CargoMain.ProductList["milk"] = CargoMain.ProductList["milk"]!!+truck.milk
        CargoMain.ProductList["potato"] = CargoMain.ProductList["potato"]!!+truck.potato
        CargoMain.ProductList["bread"] = CargoMain.ProductList["bread"]!!+truck.bread
        CargoMain.inputItems = CargoMain.inputItems+truck.cargo







    }


}