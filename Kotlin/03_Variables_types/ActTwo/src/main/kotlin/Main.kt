fun main(args: Array<String>) {

    val firstName: String = "Aleksandr"
    val lastName: String = "Ognev"
    var height: Double = 1.82
    val weight: Float = 91f

    var isChild: Boolean = height<1.50||weight<50f
    var info: String = ("Name: "+ firstName + "\nSurname: " + lastName + "\nHeight: " + height + "\nWeight: " + weight + "\nChild: " + isChild )
    println(info)
    println()
    height=1.48
    isChild=height<1.50||weight<50f
    info = ("Name: "+ firstName + "\nSurname: " + lastName + "\nHeight: " + height + "\nWeight: " + weight + "\nChild: " + isChild )
        println(info)

}
