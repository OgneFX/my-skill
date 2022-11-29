import kotlin.collections.MutableMap as MutableMap1

class TV(Model: String, Diagonal: Int) {

    var marka: String = "LG"
    var mode: String = Model
    var diagonal:Int = Diagonal

    var onOff: Boolean=false
    private set

    private val tvChannel = mutableMapOf<Int, String>()
    var helpList = listOf<String>()



    var volume: Int = 10
    private set

    private var channelNow: Int = 1



init {
        helpList = Channels.getRandomChannels()
     for(i in 0..helpList.size-1)
        tvChannel[i+1] = helpList[i]
}



    fun turnOnOff():Boolean
    {
        if(!onOff)
        {
            onOff = true
            println("TV is ON")
        }

        else
        {
            onOff = false
            println("TV is OFF")
        }

       return onOff

    }


    fun volumePlus (i: Int) :Int
    {
        if (!onOff){
            println("TV is OFF, please ON")
        }

        if(i<=0)
        {
            println("obman")
            return volume
        }
        volume += i
        if (volume>= maxVolume)
        {println("It`s max volume")
        volume = 100}
        else
        {println ("volume is $volume")}
        return volume
    }

    fun volumeMinus (i: Int) :Int
    {
        if (!onOff){
            println("TV is OFF, please ON")
        }

        if(i<=0)
        {
            println("obman")
            return volume
        }
        volume -= i
        if (volume<= 0)
        {println("It`s min volume")
            volume = 0}
        else
        {println ("volume is $volume")}
        return volume
    }

    fun channelNum (i:Int) :Int
    {
        if (i>helpList.size || i<1)
        {
            println("No such channels")


        }
        else
        {   channelNow = i
            println(tvChannel.get(channelNow))

        }

            return channelNow

    }

    fun channelInputPlus(): Int
    {
        if (!onOff)
        {
            onOff = true
            println("TV is ON")

        }
        if (channelNow>=helpList.size)
        {
            channelNow = 1
            println("Channel: "+tvChannel.get(channelNow))
        }
        else
        {
            channelNow += 1
            println("Channel: "+tvChannel.get(channelNow))
        }
        return channelNow
    }

    fun channelInputMinus(): Int
    {
        if (!onOff)
        {
            onOff = true
            println("TV is ON")

        }
        if (channelNow<=1)
        {
            channelNow = 5
            println("Channel: "+tvChannel.get(channelNow))
        }
        else
        {
            channelNow -= 1
            println("Channel: "+tvChannel.get(channelNow))
        }
        return channelNow
    }

    fun channelList ()
    {
        if (!onOff)
        {
            println("TV is OFF, please ON")
        }
        else{
            println("List of Channel")
            tvChannel.forEach { (key, value) -> println("$key $value") }
        }

    }





    companion object{
        const val maxVolume: Int = 100
    }



}