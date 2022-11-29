import kotlin.random.Random

class Fish(_energyF: Int = 30, _weight: Int = 10, _maxAgeF: Int = 10, _name: String = "Red Fish") :
    Animal(_energyF, _weight, _maxAgeF, _name) {


    override fun walk() {
        super.walk()
        println("and float")
    }

    override fun newAnimal(): Fish {
        val i = Fish(Random.nextInt(1, 10), Random.nextInt(1, 5), maxAge, name)
        println("New animal is come: energy ${i.energy}, weight ${i.weight}, max age is ${i.maxAge}, and name ${i.name}")
        return i
    }
}