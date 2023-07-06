object Products {

    val milk = object: ResoursesStat
    {
        override val weight: Int = 2
        override val timeToMove: Int = 100

    }

    val potato = object: ResoursesStat
    {
        override val weight: Int = 3
        override val timeToMove: Int = 150

    }

    val bread = object: ResoursesStat
    {
        override val weight: Int = 1
        override val timeToMove: Int = 50

    }

}