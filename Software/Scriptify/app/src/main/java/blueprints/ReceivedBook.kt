package blueprints

data class ReceivedBook (
    val id_received: Int,
    val id_user: Int,
    val idKnjige: String,
    val naziv_knjige: String,
    val autor: String,
    val Description: String,
    val cijena_knjige: Int,
    val status: String


)