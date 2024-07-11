package com.pbp.bcnctest.service

import com.pbp.bcnctest.models.Photo
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.client.RestTemplate
import java.util.*

@Service
class PhotoService {

    val restTemplate = RestTemplate()

    @Value("\${json.placeholder.url.photos}")
    lateinit var urlphotos: String

    fun getPhotoByAlbum( albumId: Int): List<Photo> {
        val photos = restTemplate.getForObject(
            "$urlphotos?albumId=$albumId",
            Array<Photo>::class.java
        )
        return photos?.toList() ?: Collections.emptyList()
    }

    fun getPhotos(): List<Photo> {
        val photos = restTemplate.getForObject(
            "$urlphotos",
            Array<Photo>::class.java
        )
        return photos?.toList() ?: Collections.emptyList()
    }

    fun getPhotoById(id: Int): Photo {
        val photos = restTemplate.getForObject(
            "$urlphotos/$id",
            Photo::class.java
        )
        return photos
    }
}