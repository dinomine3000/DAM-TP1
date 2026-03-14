package dam.virtual_library

fun main() {
    val library = Library("Central Library")
    val digitalBook = DigitalBook(
        "Kotlin in Action",
        "Dmitry Jemerov",
        2017,
        5,
        4.5,
        "PDF"
    )
    val physicalBook = PhysicalBook(
        "Clean Code",
        "Robert C. Martin",
        2008,
        3,
        650,
        true
    )
    val classicBook = PhysicalBook(
        "1984",
        "George Orwell",
        1949,
        2,
        400,
        false
    )
    val noneBook = PhysicalBook(
        "And Then There Were None",
        "Agatha Christie",
        1940,
        1,
        250,
        true
    )
    library.addBook(digitalBook)
    library.addBook(physicalBook)
    library.addBook(classicBook)
    library.addBook(noneBook)
    library.showBooks()
    println("\n--- Borrowing Books ---")
    library.borrowBook("Clean Code")
    library.borrowBook("1984")
    library.borrowBook("1984")
    library.borrowBook("1984") // Should fail - no copies left
    println("\n--- Returning Books ---")
    library.returnBook("1984")
    println("\n--- Search by Author ---")
    library.searchByAuthor("George Orwell")

    println("\n--- Member borrowing a book ---")
    val member = LibraryMember("John Strange Fake", 5, ArrayList())
    member.borrowBook("Clean code", library)
    member.borrowBook("And then there were None", library)
    member.borrowBook("Ishura", library)
}