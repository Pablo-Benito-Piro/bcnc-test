package com.pbp.bcnctest.repository

import com.pbp.bcnctest.models.Photo
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "PhotoRepository", url = "\${json.placeholder.url.photos}")
@Repository
interface PhotoRepository {
    @GetMapping("/{id}")
    fun getPhotoById(@PathVariable id: String): Photo

    @GetMapping("")
    fun getPhotosByAlbumId(@RequestParam("albumId") albumId: String): List<Photo>

    @GetMapping("")
    fun getAllPhotos(): List<Photo>
}




