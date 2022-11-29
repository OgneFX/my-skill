fun main() {
   var number: Int = readLine()?.toIntOrNull() ?: return
    if(number>0){
        println("Fibonacci with For: "+fibFor(number))
        println("Fibonacci with While: "+fibWhile(number))
        println("Fibonacci with Recursin "+fibRec(number))
    }
    else
    {
        println("invalid value entered")
        main()
    }

}



fun fibFor (numF:Int) :Int
{
    var f1:Int = 0
    var f2:Int = 1

    for(i in 1..numF)
    {
        var summfib = f1+f2
        f1=f2
        f2=summfib
    }

    return (f1)
}

fun fibWhile (numF:Int) :Int
{
    var f1:Int = 0
    var f2:Int = 1
    var i: Int = 0
    while (i<numF)
    {

        var summfib = f1+f2
        f1=f2
        f2=summfib
        i++

    }

    return (f1)
}

fun fibRec (numF: Int):Int{
    if (numF==1) return 1
    if (numF==0) return 0

    return fibRec(numF-2)+fibRec(numF-1)
}












