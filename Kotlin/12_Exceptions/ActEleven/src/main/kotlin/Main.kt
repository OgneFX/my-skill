fun main() {
    try {
        val wheel = Wheel(1f)
        wheel.pumpWheel()
        wheel.checkPressureInWheel()
    }
    catch (e: Exception){
        println ("Input other pressure")
    }
}