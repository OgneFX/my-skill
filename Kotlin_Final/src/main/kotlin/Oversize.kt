object Oversize {

    val bath = object: ResoursesStat
    {
        override val weight: Int = 50
        override val timeToMove: Int = 3500

    }

    val locker = object: ResoursesStat
    {
        override val weight: Int = 70
        override val timeToMove: Int = 4500

    }

    val bed = object: ResoursesStat
    {
        override val weight: Int = 80
        override val timeToMove: Int = 5000

    }

}