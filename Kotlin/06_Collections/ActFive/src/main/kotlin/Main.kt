

fun main() {

    val N: Int = readLine()?.toIntOrNull()?:return
    var mutMap = mutableMapOf<String, String>( )

    if(N>0){
        val list = inputNum(N)


        println(list.filter { it.startsWith("+7",true) })
        println(list.filter { it.startsWith("+7",true) }.toSet())
        println(list.filter { it.startsWith("+7",true) }.toSet().size)
        println(list.filter { it.startsWith("+7",true) }.toSet().sumOf { it.length })

        val count = list.filter { it.startsWith("+7",true) }.toSet().size



        for(index in 1..count) {

            var x: String = list[index-1]
            println("Input name who have number: " + x)
            var y:String = readLine().toString()

            mutMap.put(x,y)

        }



    }
    else
    {
        println("invalid value entered")
        main()
    }

    mutMap.forEach { key, value -> println("Abonent: $key " + " Phone Number: $value "  ) }

}

fun inputNum (imNum: Int): List<String> {
    val mutubleList = mutableListOf<String>()
    var y: String
    for (i in 1..imNum){
        y = readLine().toString()
        mutubleList.add(y)
    }

    return mutubleList
}

