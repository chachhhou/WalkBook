package fr.isen.walkbook

import fr.isen.walkbook.models.PostModel
import fr.isen.walkbook.models.UserModel

class GlobalVar {
    companion object {
        var posts: ArrayList<PostModel>? = arrayListOf()
    }
}