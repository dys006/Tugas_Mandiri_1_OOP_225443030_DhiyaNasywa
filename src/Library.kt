class Library(
    val name: String
) {
    private val items: MutableList<Item> = mutableListOf()
    private val members: MutableList<Member> = mutableListOf()
    private val transactions: MutableList<Transaction> = mutableListOf()

    val totalItems: Int
        get() = items.size

    val availableItems: Int
        get() = items.count { it.isAvailable }

    val totalMembers: Int
        get() = members.size

    val totalTransactions: Int
        get() = transactions.size

    fun addItem(item: Item) {
        items.add(item)
        println("Berhasil menambahkan item '${item.title}' ke perpustakaan.")
    }

    fun addItems(vararg newItems: Item) {
        for (item in newItems) {
            addItem(item)
        }
    }

    fun findItem(id: String): Item? {
        return items.find { it.id == id }
    }

    fun searchItems(keyword: String): List<Item> {
        return items.filter {
            it.title.contains(keyword, ignoreCase = true) ||
                    it.id.contains(keyword, ignoreCase = true)
        }
    }

    fun registerMember(id: String, name: String, email: String, phone: String): Boolean {
        if (findMember(id) != null) {
            println("Gagal! Anggota dengan ID '$id' sudah terdaftar.")
            return false
        }
        val newMember = Member(id, name, email, phone)
        members.add(newMember)
        println("Berhasil Mendaftarkan'$name'.")
        return true
    }

    fun findMember(id: String): Member? {
        return members.find { it.id == id }
    }
    fun borrowItem(memberId: String, itemId: String): Transaction? {
        val member = findMember(memberId)
        val item = findItem(itemId)

        if (member == null) {
            println("Error: Anggota dengan ID '$memberId' tidak ditemukan.")
            return null
        }
        if (item == null) {
            println("Error: Item dengan ID '$itemId' tidak ditemukan.")
            return null
        }

        val trx = member.borrowItem(item)
        if (trx != null) {
            transactions.add(trx)
        }
        return trx
    }

    fun returnItem(memberId: String, itemId: String, daysLate: Int = 0): Double {
        val member = findMember(memberId)
        val item = findItem(itemId)

        if (member == null) {
            println("Error: Anggota dengan ID '$memberId' tidak ditemukan.")
            return 0.0
        }
        if (item == null) {
            println("Error: Item dengan ID '$itemId' tidak ditemukan.")
            return 0.0
        }

        return member.returnItem(item, daysLate)
    }

    fun displayAllItems() {
        println("=== DAFTAR SELURUH ITEM ($availableItems / $totalItems Tersedia) ===")
        for (item in items) {
            item.displayInfo()
        }
    }

    fun displayAvailableItems() {
        println("=== DAFTAR ITEM TERSEDIA ===")
        val availableList = items.filter { it.isAvailable }
        if (availableList.isEmpty()) {
            println("Tidak ada item yang tersedia saat ini.")
        } else {
            for (item in availableList) {
                println("- [${item.id}] ${item.title} (${item.getItemType()}) - ${item.year}")
            }
        }
    }

    fun displayAllMembers() {
        println("Daftar Member ($totalMembers Anggota)")
        for (member in members) {
            member.displayInfo()
        }
    }

    fun displayAllTransactions() {
        println("Daftar Transasksi ($totalTransactions Transaksi)")
        for (trx in transactions) {
            trx.displayTransaction()
        }
    }

    fun displayReport() {
        val borrowedCount = totalItems - availableItems
        val grandTotalFines = members.sumOf { it.totalFines }
        println("        LAPORAN PERPUSTAKAAN            ")
        println("        $name                           ")
        println("Total Item           : $totalItems")
        println("Item Tersedia        : $availableItems")
        println("Item Dipinjam        : $borrowedCount")
        println("Total Anggota        : $totalMembers")
        println("Total Transaksi      : $totalTransactions")
        println("Total Denda          : Rp$grandTotalFines")
    }
}