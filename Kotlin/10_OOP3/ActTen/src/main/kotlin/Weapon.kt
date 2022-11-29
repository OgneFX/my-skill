object Weapon {
    var pistol = object : AbstractWeapon() {

        override var bulletCountIN: Int = 7
        override var fireType: FireType = FireType.OneShot
        override var ammoMagazine: Stack<Ammo> = Stack()
        override var haveBullet: Boolean = false
        override var ammunition: Ammo = Ammo.PISTOLBULLET

    }
    var riffle = object : AbstractWeapon() {
        override var bulletCountIN: Int = 30
        override var fireType: FireType = FireType.Queue(3)
        override var ammoMagazine: Stack<Ammo> = Stack()
        override var haveBullet: Boolean = false
        override var ammunition: Ammo = Ammo.RIFFLEBULLET


    }
    var shotgun = object : AbstractWeapon() {
        override var bulletCountIN: Int = 2
        override var fireType: FireType = FireType.OneShot
        override var ammoMagazine: Stack<Ammo> = Stack()
        override var haveBullet: Boolean = false
        override var ammunition: Ammo = Ammo.SHOTGUNBULLET


    }
}
