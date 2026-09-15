fun main() {
    println("Perpustakaan Kampus")
    val library = Library("Perpustakaan Kampus")

    println("\n TAMBAH ITEM")
    val book1 = Book("B001", "Pemrograman Kotlin", 2023, "Budi Santoso", 350, "Programming")
    val book2 = Book("B002", "Dasar-Dasar OOP", 2022, "Siti Rahayu", 280, "Education")
    val journal1 = Journal("J001", "Jurnal Teknologi Informasi", 2023, "ITB", 15, 2)
    val journal2 = Journal("J002", "Jurnal Pendidikan", 2022, "UGM", 10, 1)
    val dvd1 = DVD("D001", "Inception", 2010, "Christopher Nolan", 148, "Sci-Fi")
    val dvd2 = DVD("D002", "The Matrix", 1999, "Wachowski", 136, "Action")

    library.addItems(book1, book2, journal1, journal2, dvd1, dvd2)

    println("\nREGISTRASI ANGGOTA")
    library.registerMember("M001", "Dhiya Nasywa", "dhiya@email.com", "08123456789")
    library.registerMember("M002", "Maula Aulia", "maula@email.com", "08129876543")
    library.registerMember("M003", "Anida Putri", "anida@email.com", "08125678901")

    println("\nTAMPILKAN SEMUA ITEM")
    library.displayAllItems()

    println("\nPEMINJAMAN")
    library.borrowItem("M001", "B001") // Ahmad meminjam Pemrograman Kotlin
    library.borrowItem("M001", "D001") // Ahmad meminjam Inception
    library.borrowItem("M002", "J001") // Dewi meminjam Jurnal Teknologi Informasi
    library.borrowItem("M003", "B002") // Rizky meminjam Dasar-Dasar OOP

    println("\nTAMPILKAN ITEM TERSEDIA")
    library.displayAvailableItems()

    println("\nTAMPILKAN TRANSAKSI ANGGOTA")
    val ahmad = library.findMember("M001")
    val dewi = library.findMember("M002")

    ahmad?.displayTransactions()
    dewi?.displayTransactions()

    println("\nPENGEMBALIAN")
    library.returnItem("M001", "B001", daysLate = 0) // Ahmad mengembalikan tepat waktu
    library.returnItem("M002", "J001", daysLate = 3) // Dewi mengembalikan terlambat 3 hari

    println("\nTAMPILKAN TRANSAKSI SETELAH PENGEMBALIAN")
    ahmad?.displayTransactions()
    dewi?.displayTransactions()


    println("\n POLIMORFISME")
    val sampleItems: List<Item> = listOf(book1, journal1, dvd1)
    for (item in sampleItems) {
        println("${item.getItemType()} - Denda/hari: Rp${item.calculateFinePerDay()}")
    }

    println("\n SEALED CLASS")
    val statuses: List<TransactionStatus> = listOf(
        TransactionStatus.Borrowed,
        TransactionStatus.Returned,
        TransactionStatus.Overdue(5),
        TransactionStatus.Cancelled
    )

    for (status in statuses) {
        val message = when (status) {
            is TransactionStatus.Borrowed -> status.display()
            is TransactionStatus.Returned -> status.display()
            is TransactionStatus.Overdue -> status.display()
            is TransactionStatus.Cancelled -> status.display()
        }
        println("Status Transaksi: $message")
    }

    println("\nSMART CASTING")
    val itemTarget: Item? = library.findItem("B001")

    if (itemTarget is Book) {
        println("Item ${itemTarget.id} adalah Buku")
    } else if (itemTarget is Journal) {
        println("Item ${itemTarget.id} adalah Jurnal")
    } else if (itemTarget is DVD) {
        println("Item ${itemTarget.id} adalah DVD")
    }

    val dvdTest = itemTarget as? DVD
    if (dvdTest != null) {
        println("Casting ke DVD Berhasil: Durasi ${dvdTest.duration} menit")
    } else {
        println("Casting ke DVD Gagal (Hasil: null)")
    }

    println("\nENKAPSULASI")

    println("Enkapsulasi melindungi data:")
    println("- Properti 'isAvailable' memiliki 'private set' sehingga tidak bisa diubah langsung dari luar kelas.")
    println("- Properti 'email' dan 'phone' bersifat 'private val' sehingga tidak bisa diakses langsung (akses menggunakan getter).")

    println("\nLAPORAN AKHIR PERPUSTAKAAN")
    library.displayReport()
}