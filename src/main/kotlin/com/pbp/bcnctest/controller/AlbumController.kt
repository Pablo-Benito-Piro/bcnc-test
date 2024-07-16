package com.pbp.bcnctest.controller


import com.pbp.bcnctest.service.AlbumService
import feign.FeignException
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/album")
@Tag(name = "Album", description = "Rest API for Albums")
class AlbumController {

    @Autowired
    private lateinit var albumService: AlbumService

    @Operation(summary = "Get all albums")
    @GetMapping()
    fun getAlbums(): ResponseEntity<Any> {
        return ResponseEntity.ok(albumService.getAlbums())
    }

    @Operation(summary = "Get all albums with photos")
    @GetMapping(value = ["/all/{all}"])
    fun getAlbumsWithPhotos(@PathVariable all: String): ResponseEntity<Any> {
        try {
            val albums = albumService.getAlbumsWithPhotos(all.toBooleanStrict())
            if (albums.isEmpty()) return ResponseEntity("No albums found", HttpStatus.NOT_FOUND)
            return ResponseEntity.ok(albums)
        } catch (e: IllegalArgumentException) {
            return ResponseEntity("Invalid boolean value: $all", HttpStatus.BAD_REQUEST)
        }
    }

    @Operation(summary = "Get  albums by id")
    @GetMapping(value = ["/{id}"])
    fun getAlbumById(@PathVariable(value = "id") id: String): ResponseEntity<Any> {
        try {
            val albums = albumService.getAlbumById(id) ?: return ResponseEntity(
                "No album found with id: $id",
                HttpStatus.NOT_FOUND
            )
            return ResponseEntity.ok(albums)
        } catch (e: FeignException) {
            return ResponseEntity("No albums with Id $id", HttpStatus.NOT_FOUND)
        }
    }

    @Operation(summary = "Get album by user id")
    @GetMapping(value = ["/user/{userId}"])
    fun getAlbumsByUserId(@PathVariable userId: String): ResponseEntity<Any> {
        val albums = albumService.getAlbumsByUserId(userId)
        if (albums.isEmpty()) return ResponseEntity("No albums with User Id $userId", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(albums)
    }

}