import kotlin.random.Random

open class Animal(_energy: Int = 25, _weight: Int = 15, _maxAge: Int = 15, _name: String = "Some Animal") {

    var energy = _energy
    var weight = _weight
    var maxAge = _maxAge
    var name = _name
    var animalAge: Int = 1
    var isTooOld: Boolean = false


    fun sleep() {
        if (animalAge == maxAge || energy == 0 || weight == 0) {
            isTooOld = true
        } else {
            energy += 5
            animalAge += 1
            println("$name is sleeping")
        }
    }

    fun eat() {
        if (animalAge == maxAge || energy == 0 || weight == 0) {
            isTooOld = true
        } else {
            energy += 3
            weight += 1
            tryIncrementAge()
            println("$name is eating")
        }


    }

    open fun walk() {
        if (animalAge == maxAge || energy == 0 || weight == 0) {
            isTooOld = true
        }
        energy -= 5
        weight -= 1
        tryIncrementAge()
        println("$name is walking")
    }

    fun tryIncrementAge() {
        if (Random.nextBoolean()) {
            animalAge += 1
        }
    }

    open fun newAnimal(): Animal {
        val z = Animal(Random.nextInt(1, 10), Random.nextInt(1, 5), maxAge, name)
        println("New animal is come: energy ${z.energy}, weight ${z.weight}, max age is ${z.maxAge}, and name ${z.name}")
        return z

    }


}