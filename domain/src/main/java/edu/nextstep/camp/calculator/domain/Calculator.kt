package edu.nextstep.camp.calculator.domain


object Calculator {
    fun evaluate(expression: String) : Int =
        evaluateNonEmptyExpression(expression.filter { !it.isWhitespace() })

    private fun evaluateNonEmptyExpression(expression: String) : Int {
        if (!RegexUtils.checkExpressionIsValid(expression)) throw IllegalArgumentException("Wrong Format")
        val opsList = RegexUtils.getOperatorsList(expression)
        val numList = RegexUtils.getOperandsList(expression)

        var result = numList[0]

        numList.subList(1, numList.size).forEachIndexed { index, num ->
            result = Operator.getFromRaw(opsList[index]).evaluate(result, num)
        }

        return result
    }
}
