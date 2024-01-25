package blueprints

data class Books (
    val idKnjige: String,
    val naziv_knjige: String,
    val autor: String,
    val Description: String,
    val cijena_knjige: Int



)
{
    override fun toString(): String {
        return "$naziv_knjige - $autor"
    }
}