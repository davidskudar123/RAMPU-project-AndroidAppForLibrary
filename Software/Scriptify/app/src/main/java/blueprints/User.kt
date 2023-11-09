package blueprints

data class User(
    val id_user: Int,
    val username: String,
    val email: String,
    val password: String,
    val create_time: String,
    val address: String
)