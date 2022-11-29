import kotlin.random.Random

class Dog(_enegry: Int = 40, _weight: Int = 20, _maxAge: Int = 20, _name: String = "Tuzik") :
    Animal(_enegry, _weight, _maxAge, _name) {
    override fun walk() {
        super.walk()
        println(" and Run")
    }

    override fun newAnimal(): Dog {
        val i = Dog(Random.nextInt(1, 10), Random.nextInt(1, 5), maxAge, name)
        println("New animal is come: energy ${i.energy}, weight ${i.weight}, max age is ${i.maxAge}, and name ${i.name}")
        return i
    }

}