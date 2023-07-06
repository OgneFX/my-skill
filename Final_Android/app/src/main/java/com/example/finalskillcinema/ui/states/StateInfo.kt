package com.example.finalskillcinema.ui.states

sealed class StateInfo() {

    object Default : StateInfo()
    object Loading : StateInfo()
    object Success : StateInfo()
    class Error(private val message: String) : StateInfo()


}