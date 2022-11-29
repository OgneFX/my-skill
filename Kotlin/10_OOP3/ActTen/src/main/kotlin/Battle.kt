import kotlin.random.Random

class Battle() {

    var teamOne = Team(readLine()!!.toInt(), readln().toString())
    private var teamTwo = Team(readLine()!!.toInt(), readln().toString())
    var battleEnd = false
    var bs = BattleState.TeamParametrs(0, 0, 0, 0)
    var tow = BattleState.TeamOneWin
    var ttw = BattleState.TeamTwoWin


    private fun battleMake(statistic: BattleState) {
        when (statistic) {
            is BattleState.TeamParametrs -> {
                for (i in 0 until teamOne.team.size) {
                    statistic.healthOne += teamOne.team[i].health
                }
                for (i in 0 until teamTwo.team.size) {
                    statistic.healthTwo += teamTwo.team[i].health
                }
                println("Team One have ${statistic.healthOne} health and ${teamOne.team.size} soldiers")
                println("Team Two have ${statistic.healthTwo} health and ${teamTwo.team.size} soldiers")
                statistic.healthOne = 0
                statistic.healthTwo = 0

            }
            is BattleState.TeamOneWin -> {
                println("${teamOne.name} WIN")
                battleEnd = true
            }
            is BattleState.TeamTwoWin -> {
                println("${teamTwo.name} WIN")
                battleEnd = true
            }
        }
    }

    fun status() {

        while (teamOne.team.size > 0 && teamTwo.team.size > 0) {
            battleMake(bs)
            battle()
            println("-------------------")

        }

        if (teamOne.team.size > 0) {
            battleMake(tow)
        } else
            battleMake(ttw)


    }


    private fun battle() {


        for (i in 0 until teamOne.team.size) {
            var enemy = teamTwo.team[Random.nextInt(0, teamTwo.team.size)]
            var injure = teamOne.team[i].attack(enemy)
            enemy.getInjure(injure)
        }


        for (i in 0 until teamTwo.team.size) {
            var enemy = teamOne.team[Random.nextInt(0, teamOne.team.size)]
            var injure = teamTwo.team[i].attack(enemy)
            enemy.getInjure(injure)
        }
        val iteratorOne = teamOne.team.iterator()
        while (iteratorOne.hasNext()) {
            var item = iteratorOne.next().isKilled
            when (item) {
                true -> iteratorOne.remove()

            }

            val iteratorTwo = teamTwo.team.iterator()
            while (iteratorTwo.hasNext()) {
                var item = iteratorTwo.next().isKilled
                when (item) {
                    true -> iteratorTwo.remove()

                }

            }
            teamOne.team == iteratorOne
            teamTwo.team == iteratorTwo

        }


    }


    fun battleStart() {
        teamOne.joinToTeam()
        teamTwo.joinToTeam()
    }
}

