package com.example.calculator

import android.media.VolumeShaper.Operation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.operations.arithmetic_operations
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    //es para saber que estamos poniendo en el put
    private var expression = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //donde va a aparecer el numero
        val putText = binding?.put

        //Los numeros de los botones
        val numberButtons = listOf(
            binding?.zero, binding?.one, binding?.two, binding?.three, binding?.four,
            binding?.five, binding?.six, binding?.seven, binding?.eight, binding?.nine
        )
        //lo que hace que los numeros se puedan ver en el texto
        numberButtons.forEach { button ->
            button?.setOnClickListener {
                val Num = (it as MaterialButton).text.toString()
                expression += Num
                putText?.text = expression
            }
        }

        //boton igual
        val equalButton = binding?.equal

        //ver si puedo hacerlo mejor porque se repiten los signos y no debe ser asi
        // expreciones logaritmicas
        val operationButtons = listOf(
            binding?.addition, binding?.subtraction, binding?.multiply, binding?.divide
        )

        operationButtons.forEach { button ->
            button?.setOnClickListener {
                val operation = (it as MaterialButton).text.toString()
                expression += operation
                putText?.text = expression
            }
        }

        //
        fun Operation(button: MaterialButton) {
            when (button) {
                binding?.addition -> arithmetic_operations.ADDITION
                binding?.divide -> arithmetic_operations.DIVISION
                binding?.multiply -> arithmetic_operations.MULTIPLICATION
                binding?.subtraction -> arithmetic_operations.SUBTRACTION
                else -> null
            }
        }
    }
}