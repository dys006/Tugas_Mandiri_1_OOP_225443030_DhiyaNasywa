fun main() {
    val system = TransportSystem("Go-Transport 2024")

    val car = Car("B 1234 XYZ", "Toyota", "Innova", 2021, fuelType = "Bensin", numberOfDoors = 4)
    val motor = Motorcycle("D 5678 ABC", "Honda", "Beat", 2022, engineCapacity = 125, hasHelmet = true)
    val truck = Truck("E 9012 DEF", "Hino", "Dutro", 2020, loadCapacity = 5.0, numberOfAxles = 2)

    val driver1 = Driver("D001", "Andi", "08123456789", car)
    val driver2 = Driver("D002", "Budi", "08129876543", motor)
    val driver3 = Driver("D003", "Citra", "08125678901", truck)

    val customer1 = Customer("C001", "Dewi", "08134567890", "dewi@email.com", 100000.0)
    val customer2 = Customer("C002", "Eko", "08135678901", "eko@email.com", 50000.0)
    val customer3 = Customer("C003", "Fani", "08136789012", "fani@email.com", 200000.0)

    system.addVehicle(car)
    system.addVehicle(motor)
    system.addVehicle(truck)

    system.addDriver(driver1)
    system.addDriver(driver2)
    system.addDriver(driver3)

    system.addCustomer(customer1)
    system.addCustomer(customer2)
    system.addCustomer(customer3)

    println("==========================================")
    println("1. DATA AWAL SISTEM")
    println("==========================================")
    system.displayAllVehicles()
    println()
    system.displayAllDrivers()
    println()
    system.displayAllCustomers()
    println()

    println("PEMBUATAN PESANAN")
    val order1 = system.createOrder("C001", "D001", "Kampus A", "Mall B", 12.0)
    val order2 = system.createOrder("C002", "D002", "Stasiun", "Kantor", 8.0)
    val order3 = system.createOrder("C003", "D003", "Gudang", "Pelabuhan", 25.0)

    println("DAFTAR SEMUA ORDER")
    system.displayAllOrders()
    println()

    println(" PROSES PEMBAYARAN ORDER 1 & 2")

    if (order1 != null) {
        val qrisMethod = QRIS("QRIS-DEWI-123456789")
        val result1 = system.processPayment(order1.id, qrisMethod, order1.getTotalFare())
        println("Hasil Bayar Order 1 (Dewi): ${result1.display()}")
    }

    if (order2 != null) {
        val cashMethod = Cash()
        val kurangBayar = order2.getTotalFare() - 10000.0
        val result2Gagal = system.processPayment(order2.id, cashMethod, kurangBayar)
        println("Hasil Bayar Order 2 (Eko - Kurang Nominal): ${result2Gagal.display()}")

        customer2.topUp(50000.0)

        val ccMethod = CreditCard("1234567890123456")
        val result2Sukses = system.processPayment(order2.id, ccMethod, order2.getTotalFare())
        println("Hasil Bayar Order 2 (Eko - Kartu Kredit): ${result2Sukses.display()}")
    }
    println()

    println("5. UPDATE STATUS TRIP (COMPLETED & CANCELLED)")
    if (order1 != null) {
        system.completeOrder(order1.id)
        println("Order 1 diselesaikan.")
    }

    if (order3 != null) {
        system.cancelOrder(order3.id, "Hujan deras")
        println("Order 3 dibatalkan.")
    }
    println()

    println("STATUS AKHIR ORDER & LAPORAN PENDAPATAN")
    system.displayAllOrders()
    println()
    system.displayRevenueReport()
    println()

    println("DEMONSTRASI POLIMORFISME")
    val systemVehiclesField = TransportSystem::class.java.getDeclaredField("vehicles")
    systemVehiclesField.isAccessible = true
    @Suppress("UNCHECKED_CAST")
    val vehiclesList = systemVehiclesField.get(system) as List<Vehicle>

    for (v in vehiclesList) {
        val fareFor15Km = v.calculateFare(15.0)
        println("Tarif ${v.getType()} [${v.brand} ${v.model}] untuk 15 km: Rp$fareFor15Km")
    }
    println()

    println("DEMONSTRASI SMART CASTING")
    val driverAndi = system.findDriver("D001")
    if (driverAndi != null) {
        val vehicle = driverAndi.vehicle
        if (vehicle is Car) {
            println("Driver ${driverAndi.name} mengendarai Mobil dengan tipe bahan bakar: ${vehicle.fuelType}")
        }
    }
    println()

    println("DEMONSTRASI SEALED CLASS (WHEN EXPRESSION)")
    val sampleStatuses: List<OrderStatus> = listOf(
        OrderStatus.Waiting,
        OrderStatus.OnGoing,
        OrderStatus.Completed,
        OrderStatus.Cancelled("Driver tidak menanggapi")
    )

    for (status in sampleStatuses) {
        val info = when (status) {
            is OrderStatus.Waiting -> "Status: Menunggu pengemudi."
            is OrderStatus.OnGoing -> "Status: Perjalanan sedang berlangsung."
            is OrderStatus.Completed -> "Status: Perjalanan telah selesai."
            is OrderStatus.Cancelled -> "Status: Dibatalkan. Alasan: ${status.reason}"
        }
        println("$info | Apakah Status Akhir? ${status.isFinal()}")
    }
}