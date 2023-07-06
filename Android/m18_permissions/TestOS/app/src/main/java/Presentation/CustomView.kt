package Presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.testos.databinding.CustomImageviewBinding

class CustomView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context,attrs,defStyleAttr){

    val binding = CustomImageviewBinding.inflate(LayoutInflater.from(context))
    init {
        addView(binding.root)
    }

}