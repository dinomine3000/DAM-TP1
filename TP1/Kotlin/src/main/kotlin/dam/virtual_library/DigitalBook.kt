package dam.virtual_library

class DigitalBook(title: String, author: String, private var publicationYear: Int, availableCopies: Int, val fileSize: Double, val format: String) : Book(title, author, publicationYear, availableCopies) {
    override fun getStorageInfo(): String {
        return "{FileSize: $fileSize MB; Format: $format}"
    }

    init {
        println("Book created:\n${toString()}")
    }
}