package com.example.calculator.operations

import com.example.calculator.valou

//maneja la evaluación de expresiones aritméticas
fun evaluateExpression(expre: String):Number {


    //les da un significado a cada operacion aritmetica
    val regex = Regex("""([\d.]+|[+\-x÷])""")

    //busca y encuentra considencias en expre segun el patron de regex
    // y creando una lista de esos valores
    val tokens = regex.findAll(valou(expre).toString()).map { it.value }.toList()

    //donde se muestra la respuesta del calculo
    var result = tokens.firstOrNull()?.toDoubleOrNull() ?: 0.0

    for (i in 1 until  tokens.size step  2){

        val operand = tokens[i + 1].toDoubleOrNull()?:0.0

        result = when(tokens[i]){
            "+" -> result + operand
            "-" -> result - operand
            "x" -> result * operand
            "÷" -> if (operand != 0.0 ) result / operand else Double.NaN
            else -> result
        }
    }
    //condicion que dice que si el resultado no tiene indice y
    // es un numero entero entonces que la respuesta sea un numero entero y no un 0.0
    return if (result % 1 == 0.0) result.toInt() else result

}