open class DebitCard : BankCard() {
    override var balans = 10000f




    override fun addCash(i: Int): Float {
        balans +=i
        return balans
    }

    override fun pay(i: Int): Float {
        balans -=i
        return balans
    }

    override fun getBalans() {
        println("Balans: $balans")
    }

    override fun getInfo() {

    }
}