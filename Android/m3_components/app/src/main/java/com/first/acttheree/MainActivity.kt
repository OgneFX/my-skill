package com.first.acttheree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Leadingscope = CoroutineScope(Job() + Dispatchers.Main)

        val seekBar = findViewById<SeekBar>(R.id.Seekbar)
        val textOnCircle = findViewById<TextView>(R.id.timerCount)
        val startButton = findViewById<Button>(R.id.Startbutton)
        val circle = findViewById<ProgressBar>(R.id.progressBarCircular)
        val stopButton = findViewById<Button>(R.id.Stopbutton)
        val step = 10
        var newProgress = 10


        seekBar?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                newProgress = (progress * step)
                textOnCircle.text = newProgress.toString()
                circle.max = newProgress
                circle.progress = newProgress


            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {


            }
        })


        startButton.setOnClickListener {

            seekBar.isEnabled = false
            stopButton.visibility = View.VISIBLE
            startButton.visibility = View.INVISIBLE
             Leadingscope.launch {
                var count = newProgress
                repeat(newProgress) {
                    delay(1000)
                    count--
                    textOnCircle.text = count.toString()
                    circle.progress -= 1
                }

                if (count==0)
                {

                    seekBar.isEnabled = true
                    stopButton.visibility = View.INVISIBLE
                    startButton.visibility = View.VISIBLE
                    textOnCircle.text = newProgress.toString()
                    circle.max = newProgress
                    circle.progress = newProgress
                }
            }



        }
        stopButton.setOnClickListener {
            Leadingscope.coroutineContext.cancelChildren()
            seekBar.isEnabled = true
            stopButton.visibility = View.INVISIBLE
            startButton.visibility = View.VISIBLE
            textOnCircle.text = newProgress.toString()
            circle.max = newProgress
            circle.progress = newProgress

        }


    }


}