package dam.virtual_library

data class LibraryMember(val name: String, val membershipId: Int, var borrowedBooks: ArrayList<String>){

    fun borrowBook(book: String, library: Library){
        if(!library.borrowBook(book)) return
        if(!borrowedBooks.contains(book)) borrowedBooks.add(book)
    }    
}

