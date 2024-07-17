package com.pbp.bcnctest.service

import com.pbp.bcnctest.models.Album
import com.pbp.bcnctest.repository.AlbumRepository
import com.pbp.bcnctest.repository.PhotoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service



@Service
class AlbumService(@Autowired val albumRepository: AlbumRepository, @Autowired val photoRepository: PhotoRepository) {



    fun getAlbums(): List<Album> {
        val albums = albumRepository.getAllAlbums()
        return albums
    }

    fun getAlbumsWithPhotos(): List<Album> {
        val albums = albumRepository.getAllAlbums()
        val photos = photoRepository.getAllPhotos()
        val photosByAlbumId = photos.groupBy { it.albumId }
        albums.forEach { album ->
            album.photos = photosByAlbumId[album.id] ?: emptyList()
        }
        return albums.toList()
    }

    fun getAlbumsByUserId(userId: String): List<Album> {
        val albums = albumRepository.getAlbumByUserId(userId)
        return albums
    }

    fun getAlbumById(id: String): Album? {
        val album = albumRepository.getAlbumById(id)
        return album
    }
}