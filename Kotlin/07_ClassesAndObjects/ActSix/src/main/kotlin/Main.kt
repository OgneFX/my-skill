fun main() {

    var tv = TV("One", 60)

    println("New TV brand by ${tv.marka}, model ${tv.mode} and ${tv.diagonal} diagonal present")
    println()
    Thread.sleep(1000)
           tv.turnOnOff()
    Thread.sleep(1000)

    tv.channelList()
    println()
    Thread.sleep(1000)
    println("Please input channel")
    tv.channelNum(readLine()?.toIntOrNull()?:return)
    println()
    Thread.sleep(1000)
    println("Change channel plus 3")
    for(i in 1..3)
    {
        tv.channelInputPlus()
        Thread.sleep(1000)
    }
    Thread.sleep(1000)
    println()
    tv.turnOnOff()
    println()
    Thread.sleep(1000)
       println("Change channel minus 6")
    for(i in 1..6)
    {
        tv.channelInputMinus()
        Thread.sleep(1000)
    }
    println()
    Thread.sleep(1000)
    println("Change volume on 15 step 2")
    for(i in 1..15)
    {
        tv.volumePlus(2)
        Thread.sleep(1000)
    }
    println()
    Thread.sleep(1000)
    println("Change volume on -10 step 1")
    for(i in 1..15)
    {
        tv.volumeMinus(1)
        Thread.sleep(1000)
    }

    println()

    var tv2 = TV("Two", 65)

    println("New TV brand by ${tv2.marka}, model ${tv2.mode} and ${tv2.diagonal} diagonal present")
    println()
    Thread.sleep(1000)
    tv2.turnOnOff()
    Thread.sleep(1000)

    tv2.channelList()
    println()
    Thread.sleep(1000)
    println("Please input channel")
    tv2.channelNum(readLine()?.toIntOrNull()?:return)
    println()
    Thread.sleep(1000)
    println("Change channel plus 3")
    for(i in 1..3)
    {
        tv2.channelInputPlus()
        Thread.sleep(1000)
    }
    Thread.sleep(1000)
    println()
    tv2.turnOnOff()
    println()
    Thread.sleep(1000)
    println("Change channel minus 6")
    for(i in 1..6)
    {
        tv2.channelInputMinus()
        Thread.sleep(1000)
    }
    println()
    Thread.sleep(1000)
    println("Change volume on 15 step 2")
    for(i in 1..15)
    {
        tv2.volumePlus(2)
        Thread.sleep(1000)
    }
    println()
    Thread.sleep(1000)
    println("Change volume on -10 step 1")
    for(i in 1..15)
    {
        tv2.volumeMinus(1)
        Thread.sleep(1000)
    }

    println("Show is over, THX")
}