import kotlin.math.roundToInt

open class CreditCard : BankCard() {
    override var balans: Float = 10000f
    open var creditLimit: Float = 50000f
    open var creditBalans: Float = 50000f


    override fun addCash(i: Int): Float {
        var a = 0
        var b = i
        if (creditBalans <= creditLimit) {
            a = (creditLimit - creditBalans).toInt()
            if (a > i) {
                creditBalans += i
                return creditBalans
            } else {
                b = i - a
                balans += b
                creditBalans = creditLimit
            }

        }
        return balans
    }

    override fun pay(i: Int): Float {
        var a = 0

        if (balans > i) {
            balans-=i
            return balans
        }
        else
        {
            a = (balans-i).roundToInt()
            creditBalans += a
            balans=0f
        }
        return creditBalans
    }

    override fun getBalans() {
        println("Balans: $balans")
        println("Credit Balans: $creditBalans")
    }

    open override fun getInfo() {

    }
}