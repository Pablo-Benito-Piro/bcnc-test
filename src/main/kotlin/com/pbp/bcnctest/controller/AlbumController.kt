package com.pbp.bcnctest.controller

import com.pbp.bcnctest.models.Album
import com.pbp.bcnctest.service.AlbumService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/albums")
class AlbumController {

    @Autowired
    private lateinit var albumService: AlbumService

    @Operation(summary = "Get all albums")
    @GetMapping
    fun getAlbums(): List<Album> {
        return albumService.getAlbums()
    }

    @Operation(summary = "Get all albums with photos")
    @RequestMapping(value= [""], params = ["all"])
    fun getAlbumsWithPhotos(@RequestParam all :Boolean): List<Album> {
        return albumService.getAlbumsWithPhotos(all)
    }

    @Operation(summary = "Get  albums by id")
    @RequestMapping(value= ["/{id}"])
    fun getAlbumById(@PathVariable(value="id") id :String): Album {
        return albumService.getAlbumById(id)
    }

    @Operation(summary = "Get albums by user id")
    @RequestMapping(value= [""], params = ["userId"])
    fun getAlbumsByUserId(@RequestParam userId: String): List<Album> {
        return albumService.getAlbumsByUserId(userId)
    }


}