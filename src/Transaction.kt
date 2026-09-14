import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Transaction(
    val id : String,
    val item : Item,
    val member : Member,
    val borrowDate: String = LocalDateTime.now().toString()

){
    var status: TransactionStatus = TransactionStatus.Borrowed

    fun returnItem(daysLate: Int): Double{
        if (status.isFinal()){
            println("Transaksi Gagal!")
            return 0.0
        }
        val fine = item.returnItem(daysLate)

        status = if (daysLate > 0){
            TransactionStatus.Overdue(daysLate)
        }else{
            TransactionStatus.Returned
        }
        return fine
    }
    fun cancel():Unit {
        if (status.isFinal()) {
            println("Transaksi Gagal!")
            return
        }
        status = TransactionStatus.Cancelled
        item.returnItem(0)
        println("Transaksi '$id' dibatalkan.")
    }
        fun displayTransaction(){
            println("ID Transaksi: $id")
            println("Item: ${item.title} (${item.getItemType()})")
            println("Peminjam: ${member.name}")
            println("Tanggal Pinjam : $borrowDate")
            println("Status: ${status.display()}")

    }
}
