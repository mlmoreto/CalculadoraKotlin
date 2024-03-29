package br.edu.ifsp.scl.calculadorasdmkt.utils

/* Classe de enumeração para constantes de operadores */
// No enum class nao me interessa qual eh o valor
enum class Operador {
    RESULTADO, ADICAO, SUBTRACAO, MULTIPLICACAO, DIVISAO, LIMPEZARECENTE, LIMPEZATOTAL, RAIZQUADRADA, PORCENTAGEM
}

/* Singleton que calcula operações aritméticas básicas */
object Calculadora {
    // primeiro operando
    var operando: Float = 0.0f

    // operador que será aplicado entre primeiro e segundo operando
    var operador: Operador =
        Operador.RESULTADO

    /* calcula um valor de retorno com base no operando e operador já existentes, novo valor
     e atualiza valor de operando e operador */
    fun calcula(valor: Float, operador: Operador): Float {
        when (Calculadora.operador) {
            Operador.RESULTADO -> operando = valor
            Operador.ADICAO -> operando += valor
            Operador.SUBTRACAO -> operando -= valor
            Operador.MULTIPLICACAO -> operando *= valor
            Operador.DIVISAO -> operando /= valor
            Operador.LIMPEZARECENTE -> operando = 0F
            Operador.RAIZQUADRADA -> operando = Math.sqrt(valor.toDouble()).toFloat()
            Operador.PORCENTAGEM -> operando *= (valor / 100)
        }
        Calculadora.operador = operador
        return operando
    }

    fun limpezaRecente(valor: Float, operador: Operador): Float{
        return 0F
    }

    fun limpezaTotal(): Float{
        operando = 0F
        return operando
    }

    /*fun calculaRaizQuadrada(valor: Float): Float{
        return Math.sqrt(valor.toDouble()).toFloat()
    }*/

    fun calculaPorcentagem(resultadoAnterior: Float, valor: Float): Float{
        return resultadoAnterior * (valor / 100)
    }

}