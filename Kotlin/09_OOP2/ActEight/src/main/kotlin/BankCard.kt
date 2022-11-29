abstract class BankCard {

    abstract val balans : Float

    abstract fun addCash(i: Int): Float
    abstract fun pay (i: Int): Float
    abstract fun getBalans ()
    abstract fun getInfo ()



}