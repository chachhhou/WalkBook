package fr.isen.walkbook.models

data class UserModel(
    var lastName: String? = null,
    var firstName: String? = null,
    var age: Int? = null,
    var born: Int? = null,
    var description: String? = null,
    var userPicture: String? = null

)