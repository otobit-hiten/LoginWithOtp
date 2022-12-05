package com.example.loginwithotp


data class LoginResponseModel(
    val code: Int = 0,
    val message: String = "",
    val user: User = User()
)
{

    data class User(
        val email: String = "",
        val id: String = "",
        val mobile: String = "",
        val name: String = "",
    )

}

