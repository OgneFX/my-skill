import kotlin.math.roundToInt

class CreditCardWithBonus : CreditCard() {
    override var balans: Float = 5000f
    override var creditBalans: Float = 50000f
    override var creditLimit: Float = 50000f

    var procentCashBack = 5f
    var procentBonus = 1f
    var procentAcc = 0.05f

    var cashBack = 0.0f
    var bonusPoints = 0.0f
    var moneyAccumulator = 0.0f


    override fun addCash(i: Int): Float {
        var a = 0
        var b = i
        var z = (i * procentAcc) / 100
        moneyAccumulator += z

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
        var z: Float
        var y: Float
        if (balans > i || i >= 5000) {
            z = (i * procentCashBack) / 100
            cashBack += z
            y = (i * procentBonus) / 100
            bonusPoints += y
            balans -= i
            return balans
        } else {
            y = (i * procentBonus) / 100
            bonusPoints += y
            a = (balans - i).roundToInt()
            creditBalans += a
            balans = 0f
        }
        return creditBalans
    }


    override fun getBalans() {
        println("Balans: $balans")
        println("Credit Balans: $creditBalans")
    }

    override fun getInfo() {
        println("Balans: $balans")
        println("Credit Balans: $creditBalans")
        println("Accumulate Money: $moneyAccumulator")
        println("CashBack: $cashBack")
        println("Bonus points: $bonusPoints")
    }

}