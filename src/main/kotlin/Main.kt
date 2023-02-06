fun main() {

    when(readlnOrNull()){
        "/start" -> println("Привет")
        "/help" -> println("Доступные команды 👩🏻‍💻 \"/start\" \"/end\"")
        "/end" -> return println("Goodbye!")
        else -> println(
            "программа не может обработать запрос. наберите /help")
    }
}