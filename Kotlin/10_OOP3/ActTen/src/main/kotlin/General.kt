import kotlin.random.Random

class General : AbstractWarrior() {
    override var isKilled: Boolean = false
    override var invokeChance: Int = Random.nextInt(0, 15)
    override var maxHealth: Int = 100
    override var health: Int = 100
    override var accuracy: Int = Random.nextInt(14, 65)
    override var weapon: AbstractWeapon = Weapon.shotgun


}