package com.first.acttheree

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.coroutines.*

private const val KEY_COUNT = "count"

class MainActivity : AppCompatActivity() {

    var count = 0
    var newProgress = 10
    lateinit var startButton: Button
    lateinit var seekBar: SeekBar
    lateinit var textOnCircle: TextView
    lateinit var circle: ProgressBar
    lateinit var stopButton: Button
    val Leadingscope = CoroutineScope(Job() + Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val step = 10


        startButton = findViewById(R.id.Startbutton)
        seekBar = findViewById(R.id.Seekbar)
        textOnCircle = findViewById(R.id.timerCount)
        circle = findViewById(R.id.progressBarCircular)
        stopButton = findViewById(R.id.Stopbutton)




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
            count = newProgress
            Leadingscope.launch {
                coroutineLaunch()
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

    suspend fun coroutineLaunch() {
        Leadingscope.launch {

            repeat(count) {
                delay(1000)
                count--
                textOnCircle.text = count.toString()
                circle.progress -= 1
            }

            if (count == 0) {

                seekBar.isEnabled = true
                stopButton.visibility = View.INVISIBLE
                startButton.visibility = View.VISIBLE
                textOnCircle.text = newProgress.toString()
                circle.max = newProgress
                circle.progress = newProgress
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COUNT, count)
        outState.putInt("C", circle.progress)
        outState.putBoolean("T", seekBar.isEnabled)
        outState.putInt("A",startButton.visibility)
        outState.putInt("B",stopButton.visibility)
        outState.putBoolean("Q", Leadingscope.isActive)
        outState.putBoolean("L", startButton.isEnabled)
        outState.putInt("Z", newProgress)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        count = savedInstanceState.getInt(KEY_COUNT, count)
        textOnCircle.text = count.toString()
        circle.progress = savedInstanceState.getInt("C", circle.progress)
        seekBar.isEnabled = savedInstanceState.getBoolean("T",seekBar.isEnabled)
        startButton.visibility = savedInstanceState.getInt("A", startButton.visibility)
        stopButton.visibility = savedInstanceState.getInt("B", stopButton.visibility)
        startButton.isEnabled = savedInstanceState.getBoolean("L", startButton.isEnabled)
        newProgress = savedInstanceState.getInt("Z", newProgress)
        if (startButton.isEnabled == true)
        {
            Leadingscope.launch {
                coroutineLaunch()
            }
        }



    }




}