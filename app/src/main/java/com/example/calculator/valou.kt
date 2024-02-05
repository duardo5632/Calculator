package com.example.calculator

import com.example.calculator.operations.evaluateExpression

fun valou(expre: String): Number {

    // Define el valor de 'e'
    val eValue = 2.71828182846
    // Reemplaza todas las instancias de "e" con el valor de 'e'
    val expressionWithE = expre.replace("e", eValue.toString())

    // También puedes realizar el reemplazo para "π" si es necesario
    val value = 3.14159265359
    val expressionWithPi = expre.replace("π", value.toString())

    return expressionWithE.toDoubleOrNull() ?: 0.0
}