package com.pbp.bcnctest.models


data class Album(
    val id: Int,
    val title: String,
    val userId: String,
    var photos: List<Photo>?
)
