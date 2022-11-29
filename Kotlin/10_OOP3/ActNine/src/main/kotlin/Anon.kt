import kotlin.random.Random

object Anon : CurrencyConvert {
    override var course = Random.nextInt(1, 100).toFloat()
    override var currencyCode = "Random"


}