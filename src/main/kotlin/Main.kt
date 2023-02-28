import model.Car.Companion.toCar
import model.Command
import model.ManagerImpl
import model.Parking

fun main() {
    val parking = Parking(parkingSize = 30)
    val manager = ManagerImpl(parking)
    val start = Command.Start()
    val help = Command.Help()
    val park = Command.Park()
    val pickUp = Command.PickUp()
    val infoByCar = Command.InfoByCar()
    val infoByPlace = Command.InfoByPlace()
    val showPark = Command.ParkStats()
    val parkAllStats = Command.ParkAllStats()
    val end = Command.End()

    start.print()
    while (true) {
        if (readlnOrNull() == start.command) {
            help.print()
            while (true) {
                when (readlnOrNull()) {
                    help.command -> help.print()
                    park.command -> {
                        while (true) {
                            if (manager.canParkCar) {
                                park.print()
                                val parkingInfo = readlnOrNull().toString().split(" ")
                                if (parkingInfo.size == 4) {
                                    manager.parkCar(parkingInfo.toCar())
                                    break
                                } else {
                                    park.printIncorrectInput()
                                }
                            } else {
                                park.printFullParkingCase()
                                return
                            }
                        }
                    }

                    pickUp.command -> {
                        pickUp.print()
                        while (true) {
                            if (manager.returnCar(readlnOrNull().toString())) break
                        }
                    }

                    infoByCar.command -> {
                        infoByCar.print()
                        while (true) {
                            if (!manager.getInfoByCar(readlnOrNull().toString()))
                                infoByCar.printNotFoundCar()
                            break
                        }
                    }

                    infoByPlace.command -> {
                        infoByPlace.print()
                        while (true) {
                            val place = readlnOrNull()?.toIntOrNull()
                            if (place != null) {
                                if (place > 0) {
                                    if (!manager.getInfoByPlace(place)) infoByPlace.printNotFoundCarCase()
                                    break
                                } else {
                                    infoByPlace.printNeedPositiveIntInputCase()
                                }
                            } else {
                                infoByPlace.printNeedIntCase()
                            }
                        }
                    }
                    showPark.command->{
                        showPark.print()
                                manager.getInfoByPark()
                        }
                    parkAllStats.command -> {
                        parkAllStats.print()
                        manager.getAllStats()
                    }
                    end.command -> {
                        end.print()
                        break
                    }
                    else -> {
                        println("Некорректная команда")
                    }
                }
            }
        } else {
            println("Неопознанная команда, для начала работы введи ${start.command}")
        }
    }

}