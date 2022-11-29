class Wheel(_pressure: Float) {
    private val pressure: Float = _pressure


    fun pumpWheel(): Float {

        if (pressure < 0 || pressure > 10) {
            throw IncorrectPressure()
        } else return pressure

    }

    fun checkPressureInWheel(): Float {

        if (pressure<1.6f)
        {
            throw TooLowPressure()
        }
        else if ( pressure> 2.5f)
        {
             throw TooHeightPressure()
        }
        else
        {
            println("Wheel is normal, exploitation possible")
            return pressure
        }

    }

}