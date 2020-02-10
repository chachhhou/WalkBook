package fr.isen.walkbook.models

data class PostModel (

    val PathPicture: String? = "",
    val date: String? = "",
    val description: String? = "",
    val userId: String? = ""
)

class ListPostModel (
    var posts: ArrayList<PostModel>? = arrayListOf()
)