sealed class BattleState() {

    object TeamOneWin : BattleState()
    object TeamTwoWin : BattleState()
    data class TeamParametrs(var healthOne: Int, var healthTwo: Int, val soldierOne: Int, var soldierTwo: Int) : BattleState()

}
