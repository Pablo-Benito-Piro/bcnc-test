package com.pbp.bcnctest.service

import com.pbp.bcnctest.models.Photo
import com.pbp.bcnctest.repository.PhotoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class PhotoService(@Autowired val photoRepository: PhotoRepository) {

    fun getPhotos(): List<Photo> {
        val photos = photoRepository.getAllPhotos()
        return photos
    }

    fun getPhotoByAlbum(albumId: String): List<Photo> {
        val photos = photoRepository.getPhotosByAlbumId(albumId)
        return photos.toList()
    }

    fun getPhotoById(id: String): Photo? {
        val photos = photoRepository.getPhotoById(id)
        return photos
    }
}