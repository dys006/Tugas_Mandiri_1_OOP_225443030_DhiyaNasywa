import java.util.UUID

class TransportSystem(val name: String) {
    private val vehicles: MutableList<Vehicle> = mutableListOf()
    private val drivers: MutableList<Driver> = mutableListOf()
    private val customers: MutableList<Customer> = mutableListOf()
    private val orders: MutableList<Order> = mutableListOf()
    private val payments: MutableList<Payment> = mutableListOf()

    fun addVehicle(vehicle: Vehicle) {
        vehicles.add(vehicle)
    }

    fun addDriver(driver: Driver) {
        drivers.add(driver)
    }

    fun addCustomer(customer: Customer) {
        customers.add(customer)
    }

    fun findVehicle(plateNumber: String): Vehicle? {
        return vehicles.find { it.plateNumber.equals(plateNumber, ignoreCase = true) }
    }

    fun findDriver(id: String): Driver? {
        return drivers.find { it.id.equals(id, ignoreCase = true) }
    }

    fun findCustomer(id: String): Customer? {
        return customers.find { it.id.equals(id, ignoreCase = true) }
    }

    fun createOrder(
        customerId: String,
        driverId: String,
        pickup: String,
        dest: String,
        distance: Double
    ): Order? {
        val customer = findCustomer(customerId)
        val driver = findDriver(driverId)

        if (customer != null && driver != null) {
            val orderId = "ORD-${UUID.randomUUID().toString().take(6)}"
            val newOrder = Order(
                id = orderId,
                customer = customer,
                driver = driver,
                pickupLocation = pickup,
                destination = dest,
                distanceKm = distance
            )
            orders.add(newOrder)
            return newOrder
        }
        return null
    }

    fun processPayment(
        orderId: String,
        method: PaymentMethod,
        paidAmount: Double
    ): PaymentResult {
        val order = orders.find { it.id == orderId }
            ?: return PaymentResult.Failed("Order tidak ditemukan!", 404)

        val payment = Payment(order)
        payment.setMethod(method.name)
        val result = method.processPayment(paidAmount)

        if (result is PaymentResult.Success) {
            payment.processPayment(paidAmount)
            order.startTrip()
        }

        payments.add(payment)
        return result
    }

    fun completeOrder(orderId: String): Boolean {
        val order = orders.find { it.id == orderId }
        return order?.completeTrip() ?: false
    }

    fun cancelOrder(orderId: String, reason: String): Boolean {
        val order = orders.find { it.id == orderId }
        return order?.cancelTrip(reason) ?: false
    }

    fun displayAllVehicles() {
        println("=== DAFTAR SEMUA KENDARAAN ===")
        vehicles.forEach {
            it.displayInfo()
            println("-----------------------------")
        }
    }

    fun displayAllDrivers() {
        println("=== DAFTAR SEMUA DRIVER ===")
        drivers.forEach {
            it.displayInfo()
            println("-----------------------------")
        }
    }

    fun displayAllCustomers() {
        println("=== DAFTAR SEMUA CUSTOMER ===")
        customers.forEach {
            it.displayInfo()
            println("-----------------------------")
        }
    }

    fun displayAllOrders() {
        println("=== DAFTAR SEMUA ORDER ===")
        orders.forEach {
            it.displayOrder()
            println("-----------------------------")
        }
    }

    fun displayRevenueReport() {
        val totalRevenue = orders
            .filter { it.getStatus() == OrderStatus.Completed.display() }
            .sumOf { it.getTotalFare() }

        println("=== LAPORAN PENDAPATAN ===")
        println("Sistem Transportasi: $name")
        println("Total Pendapatan (Order Selesai): Rp$totalRevenue")
    }
}