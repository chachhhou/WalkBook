package fr.isen.walkbook.models

data class PostModel (

    val PathPicture: String? = null,
    val date: String? = null,
    val description: String? = null,
    val userId: String? = null
)

class ListPostModel (
    var posts: ArrayList<PostModel>? = arrayListOf()
)