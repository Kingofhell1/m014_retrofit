package com.example.m014_retrofit
// пользователь
data class User(val gender: String,
                val name: Name,
                val email: String,
                val phone: String,
                val picture: Image,) {

    val nameComposite: String
        get() = "${name.title} ${name.first} ${name.last}"


}
// Данные ФИО
data class Name(val title: String,
                val first: String,
                val last: String)

// Данные фото
data class Image(val large: String,
                   val medium: String,
                   val thumbnail: String)


data class UserResponse(
    val results: List<User>
)