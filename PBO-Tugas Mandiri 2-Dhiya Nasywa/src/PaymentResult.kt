sealed class PaymentResult {
    abstract fun display(): String

    data class Success(val transactionId: String, val timestamp: String) : PaymentResult() {
        override fun display(): String {
            return "Pembayaran Berhasil! [ID Transaksi: $transactionId, Waktu: $timestamp]"
        }
    }

    data class Failed(val reason: String, val errorCode: Int) : PaymentResult() {
        override fun display(): String {
            return "Pembayaran Gagal! [Alasan: $reason, Kode Error: $errorCode]"
        }
    }

    object Pending : PaymentResult() {
        override fun display(): String {
            return "Pembayaran Sedang Diproses..."
        }
    }
}