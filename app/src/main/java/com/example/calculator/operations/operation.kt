package com.example.calculator.operations

class operation(private val arithmeticOperations: arithmetic_operations) {

    fun evaluateExpression(expre: String) {

        val tokens = expre.split(" ")

        val result = tokens[0].toDoubleOrNull()?: 0.0

        for (i in 1 until  tokens.size step  2){
            val operator = tokens[i]


        }
    }

}