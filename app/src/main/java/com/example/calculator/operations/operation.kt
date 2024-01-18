package com.example.calculator.operations

import kotlin.time.times

class operation(private val arithmeticOperations: arithmetic_operations) {

    fun evaluateExpression(expre: String):Double {

        val tokens = expre.split(" ")

        var result = tokens[0].toDoubleOrNull() ?: 0.0

        for (i in 1 until  tokens.size step  2){
            val operator = tokens[i]
            val operand = tokens[i + 1].toDoubleOrNull()?:0.0

            result = when(operator){
                "+" -> result + operand
                "-" -> result - operand
                "x" -> result * operand
                "÷" -> if (operand != 0.0) result / operand else Double.NaN
                else -> result
            }

        }
        return result
    }
}