package model

data class Parking(
    val parkingSize: Int,
    val cars: MutableMap<Int, Car?> = mutableMapOf(),
) {

    init {
        for (i in 1..parkingSize) {
            cars[i] = null
        }
    }

    val countCarsOnParking: Int get() = cars.values.filterNotNull().size
    fun getEmptyParkingPlace(car: Car): Int = cars.filterValues { it == car }.keys.first()
}