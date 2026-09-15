class DVD(
    id: String,
    title: String,
    year: Int,
    val director: String,
    val duration: Int,
    val genre: String,
): Item(id, title, year) {
    override fun calculateFinePerDay(): Double= 5000.0
    override fun getItemType(): String= "DVD"
    override fun getMaxBorrowDays(): Int= 3

    override fun displayInfo() {
        super.displayInfo()
        println("Sutradara : $director")
        println("Durasi : $duration")
        println("Genre : $genre")
    }
}