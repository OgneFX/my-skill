import kotlin.random.Random

class Captain : AbstractWarrior() {
    override var isKilled: Boolean = false
    override var invokeChance: Int = Random.nextInt(0, 15)
    override var maxHealth: Int = 80
    override var health: Int = 80
    override var accuracy: Int = Random.nextInt(10, 60)
    override var weapon: AbstractWeapon = Weapon.riffle


}