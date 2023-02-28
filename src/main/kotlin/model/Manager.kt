package model

import kotlin.random.Random

interface Manager {

    val parking: Parking
    fun parkCar(car: Car)

    fun returnCar(ownerName: String): Boolean

    fun getInfoByCar(carNumber: String): Boolean

    fun getInfoByPlace(place: Int): Boolean

    fun getInfoByPark(): Boolean

    fun getAllStats()

    val canParkCar: Boolean
}

class ManagerImpl(override val parking: Parking) : Manager {

    private var numberOfParkedCars = 0
    override val canParkCar: Boolean
        get() = parking.countCarsOnParking < parking.parkingSize

    override fun parkCar(car: Car) {
        while (true) {
            val randomPlace = Random.nextInt(parking.parkingSize + 1)
            if (!parking.cars.filterValues { it != null }.keys.contains(randomPlace)) {
                parking.cars[randomPlace] = car
                println(
                    "Авто - ${car.name}, Номер - ${car.number}, Цвет - ${car.colorName}, Владелец - ${car.owner.name} запаркован на место P-${
                        parking.getEmptyParkingPlace(
                            car
                        )
                    }"
                )
                printAllParkingInfo()
                numberOfParkedCars++
                break
            }
        }
    }

    override fun returnCar(ownerName: String): Boolean {
        val car: Car? = parking.cars.values.find { it?.owner?.name == ownerName }
        return if (car != null) {
            parking.cars[parking.getEmptyParkingPlace(car)] = null
            println("Авто - ${car.name}, Номер - ${car.number}, Цвет - ${car.colorName}, Владелец - ${car.owner.name}, возвращен владельцу")
            printAllParkingInfo()
            true
        } else {
            println("Автомобиль по владельцу не найден, введи другое имя владельца")
            false
        }
    }


    override fun getInfoByCar(carNumber: String): Boolean {
        val car = parking.cars.values.find { it?.number == carNumber }
        return if (car != null) {
            println("Авто с номером $carNumber припаркован на место P-${parking.getEmptyParkingPlace(car)}")
            true
        } else {
            false
        }
    }

    override fun getInfoByPlace(place: Int): Boolean {
        val car = parking.cars[place]
        return if (car != null) {
            println("Авто - ${car.name}, Номер - ${car.number}, Цвет - ${car.colorName}, Владелец - ${car.owner.name} запаркован на место P-${place}")
            true
        } else {
            false
        }
    }

    override fun getInfoByPark():Boolean{
        var k = 0
        if (parking.cars.values.isNotEmpty()) {
            parking.cars.toSortedMap().forEach {
                if (it.value != null) {
                    println("Место P-${it.key} Номер - ${it.value?.number}, Владелец - ${it.value?.owner?.name}")
                    k++
                } else{
                    println("Место P-${it.key} - свободно")
                }
                println("за все время машин прпарковано $k")
            }
        }
        return true
    }

    override fun getAllStats() {
       println("Количество припаркованных машин за время работы: $numberOfParkedCars ")
    }

//    override fun getInfoByPark(parking: Parking): Boolean {
//        val park = parking.cars.values
//    }

    private fun printAllParkingInfo() {
        println("--- --- --- ---")
        println("Сейчас свободно ${parking.parkingSize - parking.countCarsOnParking} из ${parking.parkingSize} мест")
        if (parking.cars.values.filterNotNull().isNotEmpty()) {
            println("--- --- --- ---")
            println("Запаркованы следующие машины:")
            parking.cars.toSortedMap().forEach {
                if (it.value != null) {
                    println("Место P-${it.key}, Авто - ${it.value?.name}, Номер - ${it.value?.number}, Цвет - ${it.value?.colorName}, Владелец - ${it.value?.owner?.name}")
                }
            }
        }
    }
}