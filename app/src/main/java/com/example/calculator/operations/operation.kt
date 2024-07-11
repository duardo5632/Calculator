package com.example.calculator.operations

// Función para evaluar la expresión
fun evaluateExpression(expre: String): Number {
    // Les da un significado a cada operación aritmética y los números y divide las operaciones aritméticas con los números
    val regex = Regex("""([\d.]+|[+\-x÷])""")

    // Busca y encuentra coincidencias en la expresión según el patrón de regex y crea una lista de esos valores
    val tokens = regex.findAll(expre).map { it.value }.toList()

    // Validar si la expresión tiene al menos un número
    if (tokens.isEmpty() || tokens.none { it.toDoubleOrNull() != null }) {
        return 0 // Retornar un valor predeterminado si la expresión es inválida
    }

    // Lista temporal para guardar los operadores aritméticos y números
    val tempTokens = mutableListOf<Any>()
    var i = 0

    // Primera pasada: resolver multiplicaciones y divisiones
    while (i < tokens.size) {
        val token = tokens[i]
        // Verifica que el token sea un número y si no son solo números, ve que tipo de operación aritmética es
        if (token.toDoubleOrNull() != null) {
            // Se agrega a tempTokens
            tempTokens.add(token.toDouble())
        } else if (token == "x" || token == "÷") {

            // Asegurarse de que hay un número a la izquierda
            if (tempTokens.isEmpty() || tempTokens.last() !is Double) {
                return 0 // Retornar un valor predeterminado si la expresión es inválida
            }

            // Agarra una parte de la lista del lado izquierdo y lo elimina de tempTokens
            val left = tempTokens.removeAt(tempTokens.size - 1) as Double
            // Se incrementan los elementos de i porque agarra un elemento de la derecha
            i++

            // Asegurarse de que hay un número a la derecha
            if (i >= tokens.size || tokens[i].toDoubleOrNull() == null) {
                return 0 // Retornar un valor predeterminado si la expresión es inválida
            }

            // Agarra un elemento del lado derecho
            val right = tokens[i].toDouble()
            val intermediateResult = when (token) {
                // Donde se multiplica
                "x" -> left * right
                // Divide
                "÷" -> if (right != 0.0) left / right else throw ArithmeticException("División por cero")
                else -> throw IllegalStateException("Operador inválido")
            }
            // Se agrega el resultado ya que como apareció una operación aritmética no se añadió a tempTokens y agregamos el resultado
            tempTokens.add(intermediateResult)
        } else {
            tempTokens.add(token)
        }
        // Se incrementan los elementos de i
        i++
    }

    // Segunda pasada: resolver sumas y restas

    // Obtener el primer número
    var result = tempTokens[0] as Double
    // Se establece al siguiente número ya que result agarró el primer número
    i = 1

    while (i < tempTokens.size) {
        // Obtener los operadores aritméticos
        val operator = tempTokens[i] as String
        // Se incrementan los elementos de i
        i++
        // Asegurarse de que hay un número a la derecha
        if (i >= tempTokens.size || tempTokens[i] !is Double) {
            return 0 // Retornar un valor predeterminado si la expresión es inválida
        }
        // Agarra el siguiente número
        val value = tempTokens[i] as Double
        // Se hace la operación y se actualiza result una vez que esté lista la suma o la resta se obtiene el resultado de esa operación
        result = when (operator) {
            "+" -> result + value
            "-" -> result - value
            else -> throw IllegalStateException("Operador inesperado")
        }
        // Se incrementan los elementos de i
        i++
    }

    return if (result % 1 == 0.0) result.toInt() else result
}