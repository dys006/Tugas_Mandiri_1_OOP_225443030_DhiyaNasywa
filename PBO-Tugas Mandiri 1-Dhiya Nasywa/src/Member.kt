
class Member (
    val id: String,
    val name: String,
    private val email: String,
    private val phone: String,
){
    private val transactions: MutableList<Transaction> = mutableListOf()

    val transactionCount: Int
        get() = transactions.size

    val totalFines: Double
        get(){
            var total = 0.0
            for (transaction in transactions){
                val status = transaction.status
                if (status is TransactionStatus.Overdue){
                    total += status.daysLate * transaction.item.calculateFinePerDay()
                }
            }
            return total
        }
    val activeBorrows: Int
        get() = transactions.count{it.status is TransactionStatus.Borrowed}

    fun getEmail(): String = email
    fun getPhone(): String = phone

    fun borrowItem(item: Item): Transaction?{
        if (!item.isAvailable){
            println("Gagal! Item tidak tersedia")
            return null
        }
        if (activeBorrows >= 3){
            println("Gagal! Member sudah mencapai batas maksimal peminjaman.")
            return null
        }
        item.borrow()

        val trxId = "TRX-${System.currentTimeMillis()}"

        val newTransaction = Transaction(
            id = trxId,
            item = item,
            member = this
        )
        transactions.add(newTransaction)
        println("Berhasil meminjam")
        return newTransaction
    }
    fun returnItem(item: Item, daysLate: Int = 0): Double{
        val activeTrx = transactions.find{
            it.item == item && it.status is TransactionStatus.Borrowed
        }
        if (activeTrx == null){
            println("Error, transaksi tidak ditemukan")
            return 0.0
        }
        return activeTrx.returnItem(daysLate)
    }
    fun getTransactions(): List<Transaction>{
        return transactions.toList()
    }
    fun displayInfo(){
        println("ID Member : $id")
        println("Nama : $name")
        println("Email : $email")
        println("Telepon : $phone")
        println("Total Peminjaman: $transactionCount kali")
        println("Pinjaman Aktif : $activeBorrows item")
        println("Total Denda : Rp$totalFines")
    }
    fun displayTransactions(){
        println("Riwayat Transaksi Member : $name ($id)")
        if (transactions.isEmpty()){
            println("Belum ada riwayat transaksi")
        }else{
            for (trx in transactions){
                println ("- [${trx.id}] ${trx.item.title} | Status: ${trx.status.display()}")
            }
        }
    }
}