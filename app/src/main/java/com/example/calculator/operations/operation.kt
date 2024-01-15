package com.example.calculator.operations

class operation(private val arithmeticOperations: arithmetic_operations) {

    fun OPERATIONS(num1: Double, num2: Double): Double{
        return when(arithmeticOperations){
            arithmetic_operations.ADDITION -> num1 + num2
            arithmetic_operations.SUBTRACTION -> num1 - num2
            arithmetic_operations.MULTIPLICATION -> num1 * num2
            arithmetic_operations.DIVISION -> num1 / num2
        }
    }

    override fun toString(): String {
        return arithmeticOperations.value.toString()
    }
}