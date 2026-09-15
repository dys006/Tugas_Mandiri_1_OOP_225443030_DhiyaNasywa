class Motorcycle(
    plateNumber: String,
    brand: String,
    model: String,
    year: Int,
    isAvailable: Boolean = true,
    val engineCapacity: Int,
    val hasHelmet: Boolean
) : Vehicle(plateNumber, brand, model, year, isAvailable) {

    override fun getType(): String {
        return "Motor"
    }

    override fun calculateFare(distanceKm: Double): Double {
        return 3000.0 + (distanceKm * 1500.0)
    }

    override fun displayInfo() {
        super.displayInfo()
        println("Kapasitas Mesin: $engineCapacity cc")
        println("Fasilitas Helm : ${if (hasHelmet) "Tersedia" else "Tidak Tersedia"}")
    }
}