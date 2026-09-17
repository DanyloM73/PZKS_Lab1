package com.danylom73

fun main(args: Array<String>) {
    val analyzer = ArithmeticExpressionAnalyzer()

    if (args.isNotEmpty()) {
        val input = args.joinToString(" ")
        if (input.trim().equals(":functions", ignoreCase = true)) {
            printSupportedFunctions()
        } else {
            printResult(input, analyzer)
        }
        return
    }

    println("\n${TerminalColors.bold(TerminalColors.cyan("Лексичний та синтаксичний аналізатор арифметичних виразів"))}")
    println("Введіть арифметичний вираз для аналізу.")
    println("Введіть :functions для перегляду переліку функцій.")
    println("Введіть :exit для завершення.")

    while (true) {
        print("\n> ")
        val expression = readlnOrNull() ?: break
        when {
            expression.trim().equals(":exit", ignoreCase = true) -> break
            expression.trim().equals(":functions", ignoreCase = true) -> printSupportedFunctions()
            else -> printResult(expression, analyzer)
        }
    }
}

private fun printSupportedFunctions() {
    println(TerminalColors.bold(TerminalColors.cyan("Доступні функції:")))
    SupportedFunctions.all().forEach { function ->
        val parameters = if (function.argumentCount == 1) "x" else "x, y"
        val argumentWord = if (function.argumentCount == 1) "аргумент" else "аргументи"
        println("  ${TerminalColors.green("${function.name}($parameters)")} — " +
            "${function.argumentCount} $argumentWord")
    }
    println("Назви функцій чутливі до регістру.")
}

private fun printResult(expression: String, analyzer: ArithmeticExpressionAnalyzer) {
    val result = analyzer.analyze(expression)

    if (result.isValid) {
        println(TerminalColors.green("Вираз коректний."))
        return
    }

    println(TerminalColors.red("Знайдено помилок: ${result.errors.size}"))
    println(ErrorHighlighter.format(expression, result.errors))
    result.errors.forEachIndexed { index, error ->
        val category = "[${error.category.displayName}]"
        val coloredCategory = when (error.category) {
            ErrorCategory.LEXICAL -> TerminalColors.yellow(category)
            ErrorCategory.SYNTACTIC -> TerminalColors.red(category)
        }
        println("${index + 1}. $coloredCategory ${error.message} (${error.line}:${error.column})")
    }
}
