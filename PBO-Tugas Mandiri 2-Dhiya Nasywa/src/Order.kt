import javax.print.attribute.standard.Destination

class Order (
    val id: String,
    val customer: Customer,
    val driver: Driver,
    val pickupLocation: String,
    val destination: String,
    val distanceKm: Double
){
    private var _status: String = "Menunggu"
    private var _totalFare: Double = 0.0

    init {
        _totalFare = driver.vehicle.calculateFare(distanceKm)
    }
    fun getStatus(): String {
        return _status
    }
    fun getTotalFare(): Double {
        return _totalFare
    }
    fun startTrip(): Boolean{
        if (_status == "Menunggu"){
            _status = "Berjalan"
            return true
        }
        return false
    }
    fun completeTrip(): Boolean{
        if (_status == "Berjalan"){
            _status = "Selesai"
            return true
        }
        return false
    }
    fun cancelTrip(reason: String): Boolean{
        if (_status == "Selesai"){
            _status = "Dibatalkan"
            println("Pesanan dibatalkan karena $reason")
            return true
        }
        return false
    }
    fun displayOrder(){
        println("Detail Pesanan")
        println("ID Pesanan       : $id")
        println("Pelanggagan      : ${customer.name}")
        println("Pengemudi        : ${driver.name} (${driver.vehicle.brand} - ${driver.vehicle.plateNumber})")
        println("Lokasi Jemput    : $pickupLocation")
        println("Tujuan           : $destination")
        println("Jarak            : $distanceKm km")
        println("Total Tarif      : Rp$_totalFare")
        println("Status           : $_status")
    }
}