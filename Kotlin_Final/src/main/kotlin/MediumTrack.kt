import kotlin.random.Random

class MediumTrack : Truck() {
    override var capacity: Int = 400
    override var cargo: Int
        get() = (this.milk * Products.milk.weight) + (this.potato * Products.potato.weight) + (this.bread * Products.bread.weight)+(this.bath*Oversize.bath.weight)+
                (this.locker * Oversize.locker.weight) + (this.bed * Oversize.bed.weight) + (this.tv * Mediumsize.tv.weight) + (this.pc * Mediumsize.pc.weight)+(this.chair * Mediumsize.chair.weight)+
                (this.phone * Smallsize.phone.weight) + (this.lamp * Smallsize.lamp.weight) + (this.scissors * Smallsize.scissors.weight)
                set(value) = TODO()
    override var timeToMove: Int
    get() = (this.milk * Products.milk.timeToMove) + (this.potato * Products.potato.timeToMove) + (this.bread * Products.bread.timeToMove)+(this.bath*Oversize.bath.timeToMove)+
            (this.locker * Oversize.locker.timeToMove) + (this.bed * Oversize.bed.timeToMove) + (this.tv * Mediumsize.tv.timeToMove) + (this.pc * Mediumsize.pc.timeToMove)+(this.chair * Mediumsize.chair.timeToMove)+
            (this.phone * Smallsize.phone.timeToMove) + (this.lamp * Smallsize.lamp.timeToMove) + (this.scissors * Smallsize.scissors.timeToMove)
        set(value) = TODO()

    override var bath: Int = 0
    override var locker: Int = 0
    override var bed: Int = 0
    override var tv: Int = 0
    override var pc: Int = 0
    override var chair: Int = 0
    override var phone: Int = 0
    override var lamp: Int = 0
    override var scissors: Int = 0
    override var milk: Int = 0
    override var potato: Int = 0
    override var bread: Int = 0



}