fun main() {

    var debitCard = DebitCardWithBonus()
    var creditCard = CreditCardWithBonus()
    debitCard.getBalans()
    println()
    debitCard.getInfo()
    println()
    debitCard.addCash(7000)
    debitCard.getInfo()
    println()
    debitCard.pay(4000)
    debitCard.getInfo()
    println()
    debitCard.pay(6000)
    debitCard.getInfo()
    println("======================================")
    creditCard.getBalans()
    println()
    creditCard.getInfo()
    println()
    creditCard.pay(10000)
    creditCard.getInfo()
    println()
    creditCard.pay(754)
    creditCard.getInfo()
    println()
    creditCard.addCash(10054)
    creditCard.getInfo()






}