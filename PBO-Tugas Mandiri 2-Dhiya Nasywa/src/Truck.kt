class Truck(
    plateNumber: String,
    brand: String,
    model: String,
    year: Int,
    isAvailable: Boolean = true,
    val loadCapacity: Double,
    val numberOfAxles: Int
) : Vehicle(plateNumber, brand, model, year, isAvailable) {

    override fun getType(): String {
        return "Truk"
    }

    override fun calculateFare(distanceKm: Double): Double {
        return 10000.0 + (distanceKm * 3500.0)
    }

    override fun displayInfo() {
        super.displayInfo()
        println("Kapasitas Muat : $loadCapacity ton")
        println("Jumlah Sumbu   : $numberOfAxles")
    }
}