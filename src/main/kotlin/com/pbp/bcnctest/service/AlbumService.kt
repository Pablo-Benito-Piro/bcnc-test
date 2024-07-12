package com.pbp.bcnctest.service

import com.pbp.bcnctest.models.Album
import com.pbp.bcnctest.models.Photo
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import java.util.*

@Service
class AlbumService {

    val restTemplate = RestTemplate()

    @Value("\${json.placeholder.url.albums}")
    lateinit var urlAlbum: String

    @Value("\${json.placeholder.url.all.photos}")
    lateinit var urlAlbumWithPhotos: String

    fun getAlbums(): List<Album> {
        val albums = restTemplate.getForObject(urlAlbum, Array<Album>::class.java)
        return albums?.toList() ?: Collections.emptyList()
    }

    fun getAlbumsWithPhotos(all: Boolean): List<Album> {
        val albums = restTemplate.getForObject(urlAlbum, Array<Album>::class.java)
        if (all) {
            for (album in albums) {
                val albumWithPhotos = restTemplate.getForObject(
                    urlAlbumWithPhotos + "?albumId=${album.id}",
                    Array<Photo>::class.java
                )
                albumWithPhotos.toList().also { album.photos = it }

            }
            return albums?.toList() ?: Collections.emptyList()
        }
        return albums?.toList() ?: Collections.emptyList()
    }

    fun getAlbumsByUserId(userId: String): List<Album> {
        val albums = restTemplate.getForObject("$urlAlbum?userId=$userId", Array<Album>::class.java)
        return albums?.toList() ?: Collections.emptyList()
    }

    fun getAlbumById(id: String): Album? {
        try {
            val albums = restTemplate.getForObject("$urlAlbum/$id", Album::class.java)
            return albums

        } catch (ex: Exception) {
            return null
        }

    }
}