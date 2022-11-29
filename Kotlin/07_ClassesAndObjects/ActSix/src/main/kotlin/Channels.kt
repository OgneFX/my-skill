import kotlin.random.Random

object Channels {



    fun getRandomChannels(): List<String> {
        var newchannel = mutableListOf("First", "Moscow", "News" ,"BreakingNews", "BadNews","VeryBadNews", "Cartoon" ,"Films","Adult","Music")
        return newchannel.shuffled().subList(0,Random.nextInt(0, newchannel.size))

    }

}