class Car(
    plateNumber: String,
    brand: String,
    model: String,
    year: Int,
    isAvailable: Boolean = true,
    val fuelType: String,
    val numberOfDoors: Int
) : Vehicle(plateNumber, brand, model, year, isAvailable) {

    override fun getType(): String {
        return "Mobil"
    }

    override fun calculateFare(distanceKm: Double): Double {
        return 8000.0 + (distanceKm * 2500.0)
    }

    override fun displayInfo() {
        super.displayInfo()
        println("Bahan Bakar : $fuelType")
        println("Jumlah Pintu: $numberOfDoors")
    }
}