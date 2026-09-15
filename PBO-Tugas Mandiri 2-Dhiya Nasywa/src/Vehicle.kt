open class Vehicle (
    val plateNumber: String,
    val brand: String,
    val model: String,
    val year: Int,
    var isAvailable: Boolean = true
){
    open fun getType() : String {
        return "Kendaraan Umum"
    }
    open fun displayInfo(){
        println("Informasi Kendaraan")
        println("Plat Nomor   : $plateNumber")
        println("Brand        : $brand")
        println("Model        : $model")
        println("Year         : $year")
        println("Ketersediaan : ${if (isAvailable) "Tersedia" else "Tidak Tersedia"}")
    }
    open fun calculateFare(distanceKm: Double): Double{
        return 5000.0 + (distanceKm * 2000.0)
    }
}
