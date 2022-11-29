import kotlin.random.Random

class Bird(_enegry: Int = 30, _weight: Int = 5, _maxAge: Int = 10, _name: String = "Black Bird") :
    Animal(_enegry, _weight, _maxAge, _name) {

    override fun walk() {
        super.walk()
        println(" and fly")
    }

    override fun newAnimal(): Bird {
        val i = Bird(Random.nextInt(1, 10), Random.nextInt(1, 5), maxAge, name)
        println("New animal is come: energy ${i.energy}, weight ${i.weight}, max age is ${i.maxAge}, and name ${i.name}")
        return i
    }

}