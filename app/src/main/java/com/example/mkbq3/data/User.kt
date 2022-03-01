package com.example.mkbq3.data

data class User(
    val _id: String,
    val firstName: String,
    val hobbies: List<String>,
    val image: String,
    val lastName: String,
    val nationalCode: String
)