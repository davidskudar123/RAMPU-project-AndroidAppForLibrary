package blueprints

data class Reviews (
    val idReview: Int,
    val idKnjige: Int,
    val review_text: String,
    val rating: Int
);