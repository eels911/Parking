package model

class Car(
    val name: String,
    val number: String,
    val colorName: String,
    val owner: Owner,
) {
    companion object {
        fun List<String>.toCar(): Car = Car(first(), this[1], this[2], Owner(last()))
    }
}