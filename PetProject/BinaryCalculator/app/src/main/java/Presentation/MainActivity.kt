package Presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.binarycalculator.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.Start_Main, BinaryCalculator.newInstance())
                .commitNow()
        }

    }
}