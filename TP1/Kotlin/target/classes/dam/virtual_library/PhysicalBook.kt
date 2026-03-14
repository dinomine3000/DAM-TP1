package dam.virtual_library

class PhysicalBook(title: String, author: String, private var publicationYear: Int, availableCopies: Int, val weight: Int, val hasHardCover: Boolean = true) : Book(title, author, publicationYear, availableCopies) {
    override fun getStorageInfo(): String {
        return "{Weight: ${weight}g; Has Hard Cover: $hasHardCover}"
    }

    //tem de se realizar estas acoes de init nas subclasses de Book pois é necessário chamar o método getStorageInfo() no nível da subclasse
    //a superclasse consegue aceder ao método, mas o método faz referência a atributos da subclasse que ainda não teriam sido inicializados.
    init {
        println("Book created:\n${toString()}")
    }
}