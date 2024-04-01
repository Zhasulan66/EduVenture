package com.example.eduventure.domain.model

data class News(
    val id: Int,
    val title: String,
    val photo1: String?,
    val photo2: String?,
    val photo3: String?,
    val description: String,
    val date: String,
)

/*{
    "id": 1,
    "title": "Something happens sometimes",
    "photo1": "https://some_url.jpg",
    "photo2": "https://some_url.png",
    "photo3": null,
    "description": "some text",
    "date": "2024-03-30T21:36:50Z"
}*/
