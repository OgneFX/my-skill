import kotlin.random.Random

abstract class AbstractWarrior : Warrior {

    abstract override var isKilled: Boolean
    abstract override var invokeChance: Int

    abstract var maxHealth: Int
    abstract var health: Int
    abstract var accuracy: Int
    abstract var weapon: AbstractWeapon

    override fun attack(enemy: Warrior): Int {
        if (!weapon.haveBullet) {
            weapon.reload()

        } else {
            var bullet = weapon.getBullet()
            if (accuracy > enemy.invokeChance) {
                return bullet.bulletDamage()
            }
        }
        return 0
    }

    override fun getInjure(damage: Int) {
        health -= damage
        if (health <= 0) isKilled = true
    }
}




