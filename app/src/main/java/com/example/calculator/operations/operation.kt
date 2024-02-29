package com.example.calculator.operations

// Función para evaluar la expresión
fun evaluateExpression(expre: String): Number {

    // Define el valor de 'e'
    val eValue = 2.71828182846
    // Reemplaza todas las instancias de "e" con el valor de 'e'
    val expressionWithE = expre.replace("e", eValue.toString())

    // Reemplaza también las instancias de "π" si es necesario
    val Pivalue = 3.14159265359
    val expressionWithPi = expressionWithE.replace("π", Pivalue.toString())



    //les da un significado a cada operacion aritmetica
    val regex = Regex("""([\d.]+|[+\-x÷√])""")

    //busca y encuentra coincidencias en la expresión según el patrón de regex
    // y crea una lista de esos valores
    val tokens = regex.findAll(expressionWithPi).map { it.value }.toList()

    //donde se muestra la respuesta del calculo
    //var result = tokens.firstOrNull()?.toDoubleOrNull() ?: 0.0

    var result = 0.0
    var operator = '+'
    var isInRoot = false


    //iteraria sobre cada elemento de la lista de tokens hasta que aparezca la raiz
    for (token in tokens) {
        when {
            token == "√" -> {
                isInRoot = true
            }
            // Se convierte en un valor Double
            token.toDoubleOrNull() != null -> {
                val value = token.toDouble()
                if (isInRoot) {
                    // Se calcula la raíz
                    val sqrtValue = Math.sqrt(value)
                    result = when (operator) {
                        '-' -> result - sqrtValue
                        'x' -> result * sqrtValue
                        else -> result + sqrtValue
                    }
                    isInRoot = false
                } else {
                    when (operator) {
                        '+' -> result += value
                        '-' -> result -= value
                        'x' -> result *= value
                        '÷' -> if (value != 0.0) result /= value else return Double.NaN
                    }
                }
            }
            else -> operator = token[0]
        }
    }


    //condicion que dice que si el resultado no tiene decimales
    // es un número entero entonces que la respuesta sea un número entero y no un 0.0
    return if (result % 1 == 0.0) result.toInt() else result
}