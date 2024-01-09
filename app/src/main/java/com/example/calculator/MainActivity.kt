package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculator.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
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

        operationButtons.forEach{ button ->
            button?.setOnClickListener {
                val operation = (it as MaterialButton).text.toString()
                expression += operation
                putText?.text = expression
            }
        }
    }
}