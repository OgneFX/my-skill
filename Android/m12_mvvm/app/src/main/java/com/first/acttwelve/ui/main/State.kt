package com.first.acttwelve.ui.main

sealed class State
{
    object Loading:State()
    object NotReady:State()
    object Ready:State()

}
