abstract class AbstractWeapon {
    abstract var bulletCountIN: Int
    abstract var fireType: FireType
    abstract var ammoMagazine: Stack<Ammo>
    abstract var haveBullet: Boolean
    abstract var ammunition: Ammo

    fun makeBullet(): Ammo {
        return ammunition
    }

    fun reload() {
        for (i in 1..bulletCountIN) {
            ammoMagazine.push(makeBullet())
        }
        haveBullet = true
    }

    fun getBullet(): Ammo {
        ammoMagazine.pop()
        if (ammoMagazine.stack.size == 0)
            haveBullet = false
        return ammunition
    }

}