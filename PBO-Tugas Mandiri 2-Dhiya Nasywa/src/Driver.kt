class Driver (
    val id: String,
    val name: String,
    val phone: String,
    val vehicle: Vehicle,
    var isActive: Boolean = true
){
    fun displayInfo(){
        println("Informasi Driver")
        println("ID Driver    : $id")
        println("Nama         : $name")
        println("No. Telepon  : $phone")
        println("Status Driver: ${if (isActive) "Aktif" else "Tidak Aktif"}")
        println("\nKendaraan Pengemudi")
        vehicle.displayInfo()
    }
    fun acceptOrder(): Boolean{
        return isActive && vehicle.isAvailable
    }
}