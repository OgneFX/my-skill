import kotlin.random.Random

class NatureReserve(_iter: Int) {
    private var iter: Int = _iter

    var rip: Int = 0
    var zoo = mutableListOf(
        Bird(),
        Bird(),
        Bird(),
        Bird(),
        Bird(),
        Fish(),
        Fish(),
        Fish(),
        Dog(),
        Dog(),
        Animal(),
        Animal(),
        Animal()
    )
    var helpList = mutableListOf<Animal>()
    fun lifeCircle() {


        for (i in 1..iter) {
            println(zoo.size)
            val iterator = zoo.iterator()
            while (iterator.hasNext()) {
                var rnd = Random.nextInt(1, 5)
                val item = iterator.next()
                when (rnd) {
                    1 -> item.sleep()
                    2 -> item.eat()
                    3 -> item.walk()
                    4 -> helpList.add(item.newAnimal())
                }

            }
            zoo.addAll(helpList)

            var iterator2 = zoo.iterator()
            while (iterator2.hasNext()) {
                val item = iterator2.next()
                if (item.isTooOld == true) {
                    iterator2.remove()
                    rip+=1
                }


            }

            if (zoo.size == 0) {
                break
                println("All animal's died")
            }



        }
        println("Animal living in zoo " + zoo.size)
        println("Animal died in zoo $rip")

    }

}