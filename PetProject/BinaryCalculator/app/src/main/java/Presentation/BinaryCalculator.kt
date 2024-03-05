package Presentation

import android.animation.ObjectAnimator
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import com.example.binarycalculator.R
import com.example.binarycalculator.databinding.FragmentBinaryCalculatorBinding

class BinaryCalculator : Fragment() {

    companion object {
        fun newInstance() = BinaryCalculator()
    }

    private var _binding: FragmentBinaryCalculatorBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BinaryCalculatorViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBinaryCalculatorBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[BinaryCalculatorViewModel::class.java]




        setupDigitButtons()

        //Кнопка удаления
        binding.delete.setOnClickListener {
            viewModel.removeLastAddressDigit()
        }

        //Изменение адресной строки
        viewModel.address.observe(viewLifecycleOwner) { address ->
            binding.addressText.text = address
        }

        viewModel.switchStates.observe(viewLifecycleOwner) { states ->
            updateSwitchesUI(states)
        }

        setupSwitchListeners()


    }

    private fun updateSwitchesUI(states: List<Boolean>) {
        val images = listOf(binding.sw1, binding.sw2, binding.sw3, binding.sw4, binding.sw5, binding.sw6, binding.sw7, binding.sw8, binding.sw9)
        states.forEachIndexed { index, state ->
            animateImage(images[index], state)
        }
    }


    private fun setupSwitchListeners() {
        val images = listOf(binding.sw1, binding.sw2, binding.sw3, binding.sw4, binding.sw5, binding.sw6, binding.sw7, binding.sw8, binding.sw9)

        images.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {
                // При клике на изображение изменяем его состояние в ViewModel
                val currentStates = viewModel.switchStates.value?.toMutableList() ?: MutableList(9) { false }
                // Инвертируем состояние для конкретного переключателя
                currentStates[index] = !currentStates[index]
                viewModel.updateAddressFromSwitches(currentStates)
            }
        }
    }

    private fun animateImage(image: AppCompatImageView, up: Boolean) {
        val translationY = if (up) -250f else 0f // Поднимаем на 250px вверх или возвращаем на исходную позицию
        ObjectAnimator.ofFloat(image, "translationY", translationY).apply {
            duration = 300 // Длительность анимации в миллисекундах
            start()
        }
    }

    private fun setupDigitButtons() {
        val buttons = listOf(binding.zero, binding.one, binding.two, binding.three, binding.four, binding.five, binding.six,
        binding.seven, binding.eight, binding.nine )

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                viewModel.appendAddressDigit(index.toString())
            }
        }

        viewModel.address.observe(viewLifecycleOwner) { address ->
            val currentInputNumber = address.toIntOrNull() ?: 0 // Преобразуем в число или 0, если пусто
            val isLimitExceededNext = currentInputNumber >= 51 // Проверяем, превышает ли текущий ввод предел
            val isExactLimit = currentInputNumber == 51 // Проверяем, равен ли ввод точному пределу

            buttons.forEachIndexed { index, button ->
                // Если address пуст, все кнопки активны. Если введено 51, активна только кнопка 1.
                // В остальных случаях блокируем кнопки, которые могут привести к превышению лимита.
                button.isEnabled = when {
                    address.isEmpty() -> true // Все кнопки активны, если address пуст
                    isExactLimit -> index == 1 // Если введено 51, активна только кнопка 1
                    isLimitExceededNext -> false // Если следующий ввод может превысить лимит, блокируем кнопку
                    else -> true // В остальных случаях кнопки активны
                }
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}