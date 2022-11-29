sealed class FireType() {
    object OneShot : FireType()
    data class Queue(var bullet: Int) : FireType()
}
