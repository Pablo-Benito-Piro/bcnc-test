package com.pbp.bcnctest.controller


import com.pbp.bcnctest.models.Photo
import com.pbp.bcnctest.service.PhotoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/photo")
@Tag(name = "Photo", description = "Rest API for Photo")
class PhotoController {

    @Autowired
    private lateinit var photoService: PhotoService

    @Operation(summary = "Get all Photos")
    @GetMapping
    fun getPhotos(): List<Photo> {
        return photoService.getPhotos()
    }

    @Operation(summary = "Get Photo by id")
    @GetMapping(value = ["/{id}"])
    fun getPhotoById(@PathVariable id: String): ResponseEntity<Any> {
        val photos= photoService.getPhotoById(id) ?: return ResponseEntity("No Photos with Id $id", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(photos)
    }

    @Operation(summary = "Get Photos by album id")
    @GetMapping(value = ["/album/{albumId}"])
    fun getPhotoByAlbumId(@PathVariable albumId: String): ResponseEntity<Any> {
        val photos= photoService.getPhotoByAlbum(albumId)
        if (photos.isEmpty()) return ResponseEntity("No Photos with Album Id $albumId", HttpStatus.NOT_FOUND)
            return ResponseEntity.ok(photos)
    }

}