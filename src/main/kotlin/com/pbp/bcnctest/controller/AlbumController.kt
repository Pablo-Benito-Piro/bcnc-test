package com.pbp.bcnctest.controller

import com.pbp.bcnctest.models.Album
import com.pbp.bcnctest.service.AlbumService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/albums")
class AlbumController {

    @Autowired
    private lateinit var albumService: AlbumService

    @Operation(summary = "Get all albums")
    @GetMapping()
    fun getAlbums():  ResponseEntity<Any> {
        val albums = albumService.getAlbums()
        if (albums.isEmpty()) return ResponseEntity("No albums found", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(albums)
    }

    @Operation(summary = "Get all albums with photos")
    @GetMapping(value = [""], params = ["all"])
    fun getAlbumsWithPhotos(@RequestParam all: Boolean):  ResponseEntity<Any> {
        val albums =albumService.getAlbumsWithPhotos(all)
        if (albums.isEmpty()) return ResponseEntity("No albums found", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(albums)
    }

    @Operation(summary = "Get  albums by id")
    @GetMapping(value = ["/{id}"])
    fun getAlbumById(@PathVariable(value = "id") id: String): ResponseEntity<Any> {
        val albums = albumService.getAlbumById(id) ?: return ResponseEntity("No albums with Id $id", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(albums)
    }

    @Operation(summary = "Get albums by user id")
    @GetMapping(value = [""], params = ["userId"])
    fun getAlbumsByUserId(@RequestParam userId: String): ResponseEntity<Any> {
        val albums = albumService.getAlbumsByUserId(userId)
        if (albums.isEmpty()) return ResponseEntity("No albums with User Id $userId", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(albums)
    }

}