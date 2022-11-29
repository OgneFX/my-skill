interface Warrior {
    var isKilled: Boolean
    var invokeChance: Int

    fun attack(i: Warrior): Int
    fun getInjure(damage: Int)
}