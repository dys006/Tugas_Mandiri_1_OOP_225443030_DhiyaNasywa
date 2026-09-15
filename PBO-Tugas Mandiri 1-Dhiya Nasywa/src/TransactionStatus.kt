sealed class TransactionStatus {

    object Borrowed : TransactionStatus(){
        override fun display(): String = "Dipinjam"
    }
    object Returned : TransactionStatus(){
        override fun display(): String = "Dikembalikan"
    }
    data class Overdue(val daysLate: Int): TransactionStatus(){
        override fun display(): String = "Terlambat ($daysLate hari)"
    }
    object Cancelled : TransactionStatus(){
        override fun display(): String = "Dibatalkan"
    }

    abstract fun display(): String
    fun isFinal(): Boolean {
        return this is Returned || this is Cancelled
    }
}