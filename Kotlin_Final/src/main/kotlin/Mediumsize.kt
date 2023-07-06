object Mediumsize {

    val tv = object: ResoursesStat
    {
        override val weight: Int = 25
        override val timeToMove: Int = 1000

    }

    val pc = object: ResoursesStat
    {
        override val weight: Int = 15
        override val timeToMove: Int = 700

    }

    val chair = object: ResoursesStat
    {
        override val weight: Int = 10
        override val timeToMove: Int = 500

    }



}