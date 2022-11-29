import kotlin.random.Random

class Team(var units: Int, var name: String) {
    var team = mutableListOf<AbstractWarrior>()


    fun joinToTeam() {

        for (i in 0 until units) {
            var random = Random.nextInt(0, 100)
            if (random in 0..10) {
                team.add(madeGeneral())
            } else if (random in 11..50) {
                team.add(madeCaptain())
            } else team.add(madeSoldier())

        }

    }


    private fun madeSoldier(): AbstractWarrior {
        return Soldier()
    }

    private fun madeCaptain(): AbstractWarrior {
        return Captain()
    }

    private fun madeGeneral(): AbstractWarrior {
        return General()
    }
}