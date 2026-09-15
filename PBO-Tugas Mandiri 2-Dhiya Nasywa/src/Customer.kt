class Customer (
    val id: String,
    val name: String,
    val phone: String,
    val email: String,
    var balance: Double = 0.0
){
    fun displayInfo(){
        println("Infromasi Pelanggan")
        println("ID Customer  : $id")
        println("Nama         : $name")
        println("No. Telepon  : $phone")
        println("Email        : $email")
        println("Saldo        : $balance")
    }
    fun topUp (amount: Double){
        if (amount > 0){
            balance += amount
            println("Top up Berhasil sebesar Rp$amount. Saldo saat ini: Rp$balance")
        }else{
            println("Nominal top up harus lebih dari 0!")
        }
    }
    fun canPay(amount: Double): Boolean{
        return balance >= amount
    }
}