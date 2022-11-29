import kotlin.random.Random

class Soldier() : AbstractWarrior() {

    override var isKilled: Boolean = false
    override var invokeChance: Int = Random.nextInt(0, 15)
    override var maxHealth: Int = 50
    override var health: Int = 50
    override var accuracy: Int = Random.nextInt(0, 60)
    override var weapon: AbstractWeapon = Weapon.pistol


}