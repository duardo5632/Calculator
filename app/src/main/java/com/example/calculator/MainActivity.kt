package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculator.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        val putText = binding?.put

        //Los numeros de los botones
        val numberButtons = listOf(
            binding?.zero, binding?.one, binding?.two, binding?.three, binding?.four,
            binding?.five, binding?.six, binding?.seven, binding?.eight, binding?.nine
        )

        numberButtons.forEach{buttonId ->
            buttonId!!.setOnClickListener{
                val currentText = putText?.text.toString()
                val clickedNumber = (it as MaterialButton).text.toString()
                val newText = "$currentText$clickedNumber"
                putText?.text = newText
            }
        }
    }
}