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
    val regex = Regex("""([\d.]+|[+\-x÷])""")

    //busca y encuentra coincidencias en la expresión según el patrón de regex
    // y crea una lista de esos valores
    val tokens = regex.findAll(expressionWithPi).map { it.value }.toList()

    //donde se muestra la respuesta del calculo
    var result = tokens.firstOrNull()?.toDoubleOrNull() ?: 0.0

    for (i in 1 until tokens.size step 2) {
        val operand = tokens[i + 1].toDoubleOrNull() ?: 0.0
        result = when (tokens[i]) {
            "+" -> result + operand
            "-" -> result - operand
            "x" -> result * operand
            "÷" -> if (operand != 0.0) result / operand else Double.NaN
            else -> result
        }
    }

    //condicion que dice que si el resultado no tiene decimales
    // es un número entero entonces que la respuesta sea un número entero y no un 0.0
    return if (result % 1 == 0.0) result.toInt() else result
}

// Función para calcular la raíz cuadrada
fun SquareRoot(number: Double): Number {
    return Math.sqrt(number)
}

fun calculateExpression(expression: String): Double {
    // Verificar si hay una raíz cuadrada en la expresión
    return if (expression.contains("√")) {
        // Extraer el número para calcular la raíz cuadrada
        val number = expression.substringAfter("√").toDouble()
        // Calcular la raíz cuadrada
        val squareRootResult = SquareRoot(number).toDouble()


        // Evaluar el resto de la expresión aritmética
        val restOfExpression = expression.substringBefore("√")

        //val nextOperation = restOfExpression.substringBefore('+')

        //si no me equivoco obtiene restOfExpression para tener el resto del codigo
        //y lo resuelve con esa parte del codigo por ejemplo 5+
        //problemente ya resuelto el problema evaluar mas
        val arithmeticResult = evaluateExpression(restOfExpression + squareRootResult).toDouble()

        // Combinar los resultados
        arithmeticResult
    } else {
        // Si no hay raíz cuadrada, evaluar la expresión aritmética completa y devolver el resultado
        evaluateExpression(expression).toDouble()
    }
}