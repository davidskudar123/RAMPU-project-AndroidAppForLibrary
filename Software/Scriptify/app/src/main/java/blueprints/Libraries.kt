package blueprints
data class Library(
    val idKnjiznice: String,
    val nazivKnjiznice: String,
    val books: List<Books>
)