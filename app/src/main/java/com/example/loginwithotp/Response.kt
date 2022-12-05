package com.example.loginwithotp

import android.icu.text.CaseMap.Title

data class Response (
    val userId : Int,
    val id : Int,
    val title: String,
    val body : String

)