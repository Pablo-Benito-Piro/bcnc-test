package com.pbp.bcnctest.repository

import org.springframework.web.bind.annotation.GetMapping
import com.pbp.bcnctest.models.Album
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam


@FeignClient(name = "albumRepository", url = "\${json.placeholder.url.albums}")
interface AlbumRepository {

    @GetMapping("")
    fun getAllAlbums(): List<Album>

    @GetMapping("/{id}")
    fun getAlbumById(@PathVariable id: String): Album

    @GetMapping("")
    fun getAlbumByUserId(@RequestParam("userId") userId: String): List<Album>


}