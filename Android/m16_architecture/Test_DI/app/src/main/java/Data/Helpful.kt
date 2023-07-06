package Data

import Entity.UsefulActivity

class Helpful(
        override val activity: String = "Hi all",
        override val type: String = "Type",
        override val participants: Int = 15,
        override val price: Float = 1.0F,
        override val link: String = "link",
        override val key: Int = 111,
        override val accessibility: Float = 0.1F) :UsefulActivity
