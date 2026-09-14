abstract class Item(
    val id: String,
    val title: String,
    val year : Int,

){
    var isAvailable : Boolean = true
        private set

    abstract fun calculateFinePerDay(): Double
    abstract fun getItemType(): String
    abstract fun getMaxBorrowDays(): Int

   open fun borrow(): Boolean {
        return if (isAvailable){
            isAvailable = false
            println("Berhasil Meminjam Item '$title'.")
            true
        }else {
            println("Gagal! Item '$title' sedang tidak tersedia")
            false
        }
    }
    open fun returnItem(daysLate: Int=0): Double{
         if (!isAvailable){
            isAvailable = true

            val totalFine = daysLate * calculateFinePerDay()

            println("Item '$title' berhasil dikembalikan.")
            if (totalFine > 0) {
                println("Keterlambatan: $daysLate hari. Total denda: Rp$totalFine.")
            }else{
                println("Pengembalian tepat waktu. Tidak ada denda.")
            }
            return totalFine
        }else{
            println("Peringatan: Item '$title' saat tidak sedang dipinjam.")
            return 0.0
        }
    }
    open fun displayInfo(){
        val status = if (isAvailable)"Tersedia" else "Dipinjam"
        println("=======================================")
        println("ID Item: $id")
        println("Jenis Item : ${getItemType()}")
        println("Judul: $title")
        println("Tahun : $year")
        println("Status : $status")
        println("Denda/hari : Rp${calculateFinePerDay()}")
        println(" Maks. Peminjaman : ${getMaxBorrowDays()} hari")
        println("=========================================")
    }
}
