package Presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class BinaryCalculatorViewModel : ViewModel() {
    private val _address = MutableLiveData<String>("")
    val address: LiveData<String> = _address

    private val _switchStates = MutableLiveData<List<Boolean>>()
    val switchStates: LiveData<List<Boolean>> = _switchStates



    fun appendAddressDigit(digit: String) {
        val currentInput = _address.value ?: ""
        val updatedInput = (currentInput + digit).toIntOrNull() ?: 0
        if (updatedInput <= 511) {
            _address.value = updatedInput.toString()
            updateSwitchStates(updatedInput)
        }
    }

    fun removeLastAddressDigit() {
        val currentInput = _address.value ?: ""
        if (currentInput.isNotEmpty()) {
            val updatedInput = currentInput.dropLast(1)
            _address.value = updatedInput
            updateSwitchStates(updatedInput.toIntOrNull() ?: 0)
        }
    }


    fun updateAddressFromSwitches(switchStates: List<Boolean>){
        val binaryString = switchStates.joinToString("") { if (it) "1" else "0" }
        _address.value = binaryString.toInt(2).toString()
        _switchStates.value = switchStates

    }

    private fun updateSwitchStates(address: Int) {
        val binaryString = address.toString(2).padStart(9, '0')
        _switchStates.value = binaryString.map { it == '1' }
    }


    }
