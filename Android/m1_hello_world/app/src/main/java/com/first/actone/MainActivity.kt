package com.first.actone
import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.first.actone.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    var counter = 0
    var maxCapacity = 50
    var nowCapacity = 0

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bunding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bunding.root)


        bunding.buttonPlus.setOnClickListener {
            counter++
            if(counter in 1..49)
            {   bunding.buttonMinus.isEnabled = true
                nowCapacity = maxCapacity - counter
                bunding.TextPlaces.setTextColor(resources.getColor(R.color.blue))
                bunding.TextPlaces.text="Осталось мест: ${nowCapacity.toString()}"
                bunding.Counter.text=counter.toString()
            }
            if (counter==50)
            {
                bunding.buttonReload.visibility = View.VISIBLE
                bunding.TextPlaces.setTextColor(resources.getColor(R.color.red))
                bunding.TextPlaces.text = "Пассажиров слишком много"
                bunding.Counter.text=counter.toString()
            }

            if(counter>50)
            {   bunding.buttonReload.visibility = View.VISIBLE
                bunding.Counter.text=counter.toString()

            }


        }

        bunding.buttonMinus.setOnClickListener {



            if (counter==0)
            {   bunding.buttonReload.visibility = View.INVISIBLE
                bunding.TextPlaces.setTextColor(resources.getColor(R.color.green))
                bunding.buttonMinus.isEnabled = false
                bunding.Counter.text=counter.toString()
                bunding.TextPlaces.text="Все места свободны"

            }

            if(counter in 1..49)
            {   counter--
                nowCapacity = maxCapacity - counter
                bunding.TextPlaces.setTextColor(resources.getColor(R.color.blue))
                bunding.TextPlaces.text="Осталось мест: ${nowCapacity.toString()}"
                bunding.Counter.text=counter.toString()
                bunding.buttonReload.visibility = View.INVISIBLE


            }


            if(counter>=50)
            {   counter--
                bunding.buttonReload.visibility = View.VISIBLE
                bunding.TextPlaces.setTextColor(resources.getColor(R.color.red))
                bunding.TextPlaces.text = "Пассажиров слишком много"
                bunding.Counter.text=counter.toString()

            }







        }

        bunding.buttonReload.setOnClickListener {
            counter = 0
            bunding.TextPlaces.setTextColor(resources.getColor(R.color.green))
            bunding.TextPlaces.text="Все места свободны"
            bunding.buttonReload.visibility = View.INVISIBLE
            bunding.Counter.text = counter.toString()
            bunding.buttonMinus.isEnabled = false

        }


    }
}