import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

interface PaymentMethod {
    val name: String

    fun processPayment(amount: Double): PaymentResult

    fun getFee(amount: Double): Double {
        return 0.0
    }
}

class CreditCard(val cardNumber: String) : PaymentMethod {
    override val name: String = "Kartu Kredit"

    override fun getFee(amount: Double): Double {
        return amount * 0.02
    }

    override fun processPayment(amount: Double): PaymentResult {
        val currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        return if (cardNumber.length >= 16) {
            PaymentResult.Success("CC-${UUID.randomUUID().toString().take(8)}", currentTime)
        } else {
            PaymentResult.Failed("Nomor kartu kredit tidak valid! Minimal 16 digit.", 400)
        }
    }
}

class QRIS(val qrCode: String) : PaymentMethod {
    override val name: String = "QRIS"

    override fun getFee(amount: Double): Double {
        return amount * 0.005
    }

    override fun processPayment(amount: Double): PaymentResult {
        val currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        return if (qrCode.length >= 10) {
            PaymentResult.Success("QRIS-${UUID.randomUUID().toString().take(8)}", currentTime)
        } else {
            PaymentResult.Failed("Kode QR tidak valid! Minimal 10 karakter.", 400)
        }
    }
}

class Cash : PaymentMethod {
    override val name: String = "Tunai"

    override fun getFee(amount: Double): Double {
        return 0.0
    }

    override fun processPayment(amount: Double): PaymentResult {
        val currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        return PaymentResult.Success("CASH-${UUID.randomUUID().toString().take(8)}", currentTime)
    }
}