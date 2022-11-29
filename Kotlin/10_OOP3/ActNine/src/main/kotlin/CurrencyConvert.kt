interface CurrencyConvert {
    var course: Float
    var currencyCode: String
    fun convertToRub(rub: Int) {
        val convertedRub = rub / course
        println("$rub rubles = $convertedRub $currencyCode")
    }

}