data class Parking(
    val parkingSize: Int,
    val parking: MutableList<Car>,
) {
    fun getCarsOnParking(): List<Car> = parking.filter { it.status == CarStatus.ON_PARKING }

    fun getEmptyParkingPlace(): Int = parkingSize - getCarsOnParking().size
}