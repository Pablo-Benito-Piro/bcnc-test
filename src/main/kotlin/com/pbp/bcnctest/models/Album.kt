package com.pbp.bcnctest.models

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Album(
    val id: Int,
    val title: String,
    val userId: String,
    var photos: List<Photo>?
)
