import kotlin.random.Random

enum class Ammo(var damage: Int, var criticalDam: Int, private val criticalChance: Int) {
    PISTOLBULLET(10, 3, 25),
    RIFFLEBULLET(15, 3, 25),
    SHOTGUNBULLET(20, 4, 10)


    ;

    fun bulletDamage(): Int {
        var helpCount: Int
        return if (damage.crtChance()) {
            helpCount = damage * criticalDam
            helpCount
        } else damage
    }


    private fun Int.crtChance(): Boolean {
        val helpCount: Int = Random.nextInt(0, 100)
        return criticalChance > helpCount
    }
}