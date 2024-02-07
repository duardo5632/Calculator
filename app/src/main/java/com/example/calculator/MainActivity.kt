package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.operations.SquareRoot
import com.example.calculator.operations.evaluateExpression
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    //es para saber que estamos poniendo en el put
    private var expression = ""
    private val historyList = mutableListOf<String>()

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

        val point = binding?.point

        point?.setOnClickListener {
            expression += "."
            putText?.text = expression
        }

        //boton igual
        val equalButton = binding?.equal

        equalButton?.setOnClickListener {
            // Llama a la función evaluateExpression para obtener el resultado
            if (expression.isNotEmpty()) {

                var result: Number

                // Verificar si hay una raíz cuadrada en la expresión
                if (expression.contains("√")) {
                    // Extraer el número para calcular la raíz cuadrada
                    val number = expression.substringAfter("√").substringBefore("+").toDouble()
                    // Calcular la raíz cuadrada
                    val squareRootResult = SquareRoot(number)
                    // Evaluar el resto de la expresión aritmética
                    val restOfExpression = expression.substringAfter("√")
                    val arithmeticResult = evaluateExpression(restOfExpression)
                    // Combinar los resultados
                    result = squareRootResult.toDouble() + arithmeticResult.toDouble()
                } else {
                    // Si no hay raíz cuadrada, evaluar la expresión aritmética completa
                    result = evaluateExpression(expression)
                }

                // Agrega la expresión y el resultado al historial
                val historyEntry = "$expression = $result"
                historyList.add(historyEntry)

                // Actualiza la variable expression con el resultado
                expression = result.toString()

                // Muestra el resultado en el TextView
                putText?.text = expression

                // Actualiza el historial en el TextView de historial
                binding?.record?.text = historyList.joinToString("\n")
            }
        }
        val remove = binding?.eliminate

        remove?.setOnClickListener {
            // Verifica que la expresión no esté vacía antes de intentar eliminar
            // el último carácter
            if (expression.isNotEmpty()) {
                // Elimina el último carácter
                expression = expression.dropLast(1)

                // Muestra la modificación
                putText?.text = expression
            }
        }

        val delete = binding?.delete

        delete?.setOnClickListener {
            if (expression.isNotEmpty()) {
                // Si expression no está vacío, eliminar todo el contenido
                expression = StringBuilder().toString()

                // Actualizar la interfaz de usuario si es necesario
                putText?.text = expression
            }
        }

        val delete_all = binding?.EraseEverything

        delete_all?.setOnClickListener {
            if (expression.isNotEmpty()) {
                // Si expression no está vacío, eliminar todo el contenido
                expression = StringBuilder().toString()

                // Actualizar la interfaz de usuario si es necesario
                putText?.text = expression
            }

            if(historyList.isNotEmpty()){
                //elimina todo
                historyList.clear()

                //lo que aparece
                binding?.record?.text = ""
            }
        }

        val list = listOf(binding?.e, binding?.Deg, binding?.tan, binding?.cos, binding?.log, binding?.pi,
            binding?.sin, binding?.pi, binding?.squareRoot, binding?.openParentheses, binding?.closeParentheses
        )

        var isVisible = false

        binding?.Showmore?.setOnClickListener {
            isVisible = !isVisible

            list.forEach{button ->
                button?.visibility = if (isVisible) View.VISIBLE else View.GONE
            }
        }

        binding?.e?.setOnClickListener {

            expression += "e"

            putText?.text = expression

        }

        binding?.pi?.setOnClickListener {

            expression += "π"

            putText?.text = expression

        }

        binding?.squareRoot?.setOnClickListener {

            expression += "√"

            putText?.text = expression


        }
    }
}
