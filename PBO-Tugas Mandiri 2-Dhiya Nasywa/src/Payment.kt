class Payment(
    val order: Order,
) {
    private var _amount: Double = order.getTotalFare()
    private var _method: String = "Tunai"

    var _isPaid: Boolean = false
    private set

    fun getAmount(): Double{
        return _amount
    }
    fun getMethod(): String{
        return _method
    }
    fun isPaid(): Boolean{
        return _isPaid
    }
    fun setMethod(method : String){
        val validMethod = listOf("Tunai","Kartu Kredit", "QRIS")
        if (validMethod.contains(method)){
            _method = method
            println("Metode pembayaran berhasil diubah ke: $_method")
        }else{
            println("Metode pembayaran tidak valid! Pilih antara : Tunai, Kartu Kredit, atau QRIS")
        }
    }
    fun processPayment(paidAmount: Double): Boolean{
        if (!_isPaid && paidAmount <= _amount){
            _isPaid = true
            println("Pembayaran berhasil diselesaikan!")
            return true
        }else if(isPaid()){
            println("Pembayaran sudah pernah dilakukan sebelumnya")
            return false
        }
        else{
            println("Pembayaran gagal! Nominal uang kurang.")
            return false
        }
    }
    fun displayPayment(){
        println("Detail Pembayaran")
        println("ID Pesanan : ${order.id}")
        println("Nominal : Rp$_amount")
        println("Metode : $_method")
        println("Status Pembayaran : ${if(_isPaid) "Lunas" else "Belum Lunas"}")
    }
}