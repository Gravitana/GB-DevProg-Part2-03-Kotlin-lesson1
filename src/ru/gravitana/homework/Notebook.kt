package ru.gravitana.homework

fun main() {
    var stopProgram = false
    var userInput: String?

    while (!stopProgram) {
        println("Введите команду")
        userInput = readlnOrNull()

        when (userInput) {
            "exit", "q" -> stopProgram = true
            "help" -> printHelp()
            else -> checkInput(userInput)
        }
    }
    println("Программа завершена")
}

fun checkInput(userInput: String?) {
    if (userInput == null) {
        return
    }

    if (userInput.startsWith("add ")) {
        parseInput(userInput)
    } else {
        println("Введённая строка не является командой")
    }
}

fun parseInput(userInput: String) {
    val result: String
    val inputLines = userInput.split(" ")

    if (inputLines.size != 4) {
        println("Некорректное количество аргументов в команде")
        return
    }

    if (inputLines[2] != "phone" && inputLines[2] != "email") {
        println("Неизвестная команда")
        return
    }

    result = if (inputLines[2] == "phone") {
        parsePhone(inputLines[3])
    } else {
        parseEmail(inputLines[3])
    }

    if (result.isBlank()) {
        println("Некорректные аргументы в команде")
        return
    }

    printResult(inputLines[1], inputLines[2], result)
}

fun parseEmail(email: String): String {
    if ( !email.contains("@") || !email.contains(".")) {
        return ""
    }

    return email
}

fun parsePhone(phone: String): String {
    if ( phone.matches(Regex("""\+?[0-9]+""")) ) {
        return phone
    }

    return ""
}

fun printResult(name: String, command: String, value: String) {
    println("-----------------------")
    println("NAME: $name")
    println("${command.uppercase()}: $value")
    println("-----------------------")
}

fun printHelp() {
    println("Список доступных команд:\n" +
            "exit\n" +
            "help\n" +
            "add <Имя> phone <Номер телефона>\n" +
            "add <Имя> email <Адрес электронной почты>")
}
