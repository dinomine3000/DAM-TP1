package dam.virtual_library

abstract class Book(val title: String, val author: String, private var publicationYear: Int, availableCopies: Int) {
    val publicationYearCategory: String
        get() = when {
            publicationYear < 1980 -> "Classic"
            publicationYear in 1980..2010 -> "Modern"
            else -> "Contemporary"
        }

    var availableCopies: Int = availableCopies
        set(value) {
            require(value >= 0)
            if (value == 0) println("Warning: Run out of book! $author, $title, ($publicationYear)")
            field = value
        }

    final override fun toString(): String {
        return "$publicationYearCategory by $author, $title ($publicationYear) - $availableCopies copies available. ${getStorageInfo()}"
    }

    //metodo a ser definido por classes que extendam desta
    abstract fun getStorageInfo(): String

    fun matchTitle(testTitle: String): Boolean {
        return title.lowercase() == testTitle.lowercase()
    }
}