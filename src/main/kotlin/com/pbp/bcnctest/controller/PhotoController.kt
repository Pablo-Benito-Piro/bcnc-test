package com.pbp.bcnctest.controller


import com.pbp.bcnctest.models.Photo
import com.pbp.bcnctest.service.PhotoService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/photos")
class PhotoController {

    @Autowired
    private lateinit var photoService: PhotoService

    @Operation(summary = "Get all photos")
    @GetMapping
    fun getPhotos(): List<Photo> {
        return photoService.getPhotos()
    }

    @Operation(summary = "Get photo by id")
    @RequestMapping(value= ["/{id}"])
    fun getPhotoById(@PathVariable id: Int): Photo {
        return photoService.getPhotoById(id)
    }
    @Operation(summary = "Get photo by album id")
    @RequestMapping(value= [""], params = ["albumId"])
    fun getPhotoByAlbumId(@RequestParam albumId: Int): List<Photo> {
        return photoService.getPhotoByAlbum(albumId)
    }

}