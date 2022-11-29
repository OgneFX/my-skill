class DebitCardWithBonus : DebitCard() {

    var procentCashBack = 5f
    var procentBonus = 1f
    var procentAcc = 0.05f

    var cashBack = 0.0f
    var bonusPoints = 0.0f
    var moneyAccumulator = 0.0f
    override var balans: Float = 5000f

    override fun addCash(i: Int): Float {
        var a = (i * procentAcc) / 100
        moneyAccumulator += a
        balans += i
        return moneyAccumulator
    }

    override fun pay(i: Int): Float {
        var a: Float
        var b: Float
        if (i >= 5000) {
            a = (i*procentCashBack)/100
            cashBack +=a
            b = (i*procentBonus)/100
            bonusPoints+=b
            balans-=i
            return balans
        }
        else {
            b = (i*procentBonus)/100
            bonusPoints+=b
            balans-=i
            return balans

        }


    }

    override fun getBalans() {
        println("Balans: $balans")
    }

    override fun getInfo() {
        println("Balans: $balans")
        println("Accumulate Money: $moneyAccumulator")
        println("CashBack: $cashBack")
        println("Bonus points: $bonusPoints")
    }


}