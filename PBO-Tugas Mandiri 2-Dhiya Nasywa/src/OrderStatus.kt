sealed class OrderStatus {
    abstract fun display(): String

    fun isFinal(): Boolean {
        return this is Completed || this is Cancelled
    }

    object Waiting : OrderStatus() {
        override fun display(): String = "Menunggu"
    }

    object OnGoing : OrderStatus() {
        override fun display(): String = "Berjalan"
    }

    object Completed : OrderStatus() {
        override fun display(): String = "Selesai"
    }

    data class Cancelled(val reason: String) : OrderStatus() {
        override fun display(): String = "Dibatalkan (Alasan: $reason)"
    }
}