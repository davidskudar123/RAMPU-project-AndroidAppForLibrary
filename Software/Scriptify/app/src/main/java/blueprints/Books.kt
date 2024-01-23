package blueprints

data class Books (
    val idKnjige: String,
    val naziv_knjige: String,
    val autor: String,
    val Description: String



)
{
    override fun toString(): String {
        return "$naziv_knjige - $autor"
    }
}