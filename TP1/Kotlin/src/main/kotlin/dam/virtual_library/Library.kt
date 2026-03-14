package dam.virtual_library

class Library(name: String) {
    private var books: ArrayList<Book> = ArrayList()

    fun addBook(book: Book){
        books.add(book)
        totalBooksAdded++
    }

    fun borrowBook(title: String): Boolean{
        for(book in books){
            if(book.matchTitle(title)){
                if(book.availableCopies > 0){
                    println("Borrowing book: $book")
                    book.availableCopies -= 1
                    return true
                } else {
                    println("Could not borrow book because there are no more copies available: $book")
                    return false
                }
            }
        }
        println("Could not find book to borrow by title: $title")
        return false
    }

    fun returnBook(title: String){
        for(book in books){
            if(book.matchTitle(title)){
                book.availableCopies += 1
                println("Returned book: $book")
                return
            }
        }
        println("Could not find book to return by title: $title")
    }

    fun showBooks() {
        if(books.isEmpty()) {
            println("The library is empty")
            return
        }
        var result: String = "Books in the library:"
        for(book in books){
            result = result.plus("\n\t-$book;")
        }
        println(result)
    }

    fun searchByAuthor(author: String){
        var result = "Books written by $author:"
        for(book in books.filter { book: Book -> book.author.lowercase().contains(author.lowercase()) }.toList()){
            result = result.plus("\n\t-$book")
        }
        println(result)
    }

    companion object LibraryTracker {
        var totalBooksAdded: Int = 0
        fun getTotalBooksCreated(): Int{
            return totalBooksAdded
        }
    }
}