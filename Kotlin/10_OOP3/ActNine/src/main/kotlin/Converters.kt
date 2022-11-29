import kotlin.random.Random

object Converters {
    private val currencyList = mutableListOf(Dollar(), Euro(), Tenge())

    fun get(currencyCode: String): Any {
        val anon = object : CurrencyConvert{
            override var course = Random.nextInt(1, 100).toFloat()
            override var currencyCode = "Random"
        }

        val firstIterator = currencyList.iterator()
        while (firstIterator.hasNext()) {
            val item = firstIterator.next()
            if (item.currencyCode == currencyCode) {
                return item.convertToRub(100)

            }


        }

        return anon.convertToRub(Random.nextInt(1, 100))


        }
    }








