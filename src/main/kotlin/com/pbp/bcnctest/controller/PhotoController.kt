package com.pbp.bcnctest.controller


import com.pbp.bcnctest.service.PhotoService
import feign.FeignException
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
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
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved all photos",
                content = [
                    Content(
                        mediaType = "application/json",
                        examples = [ExampleObject(
                            name = "Photo",
                            value = """
                                [
                                    {
                                        "id": 1,
                                        "albumId": 1,
                                        "title": "accusamus beatae ad facilis cum similique qui sunt",
                                        "url": "https://via.placeholder.com/600/92c952",
                                        "thumbnailurl": "https://via.placeholder.com/600/92c952"
                                    }
                                ]
                                """)])]
            ), ApiResponse(
                responseCode = "404",
                description = "No photo found")])
    fun getPhotos(): ResponseEntity<Any> {
        return ResponseEntity.ok(photoService.getPhotos())
    }

    @Operation(summary = "Get Photo by id")
    @GetMapping(value = ["/{id}"])
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved photo by id",
                content = [
                    Content(
                        mediaType = "application/json",
                        examples = [ExampleObject(
                            name = "Photo",
                            value = """
                                    {
                                        "id": 1,
                                        "albumId": 1,
                                        "title": "accusamus beatae ad facilis cum similique qui sunt",
                                        "url": "https://via.placeholder.com/600/92c952",
                                        "thumbnailurl": "https://via.placeholder.com/600/92c952"
                                    }
                                """)])]
            ), ApiResponse(
                responseCode = "404",
                description = "No photo found")])
    fun getPhotoById(@PathVariable id: String): ResponseEntity<Any> {
        try {
            val photos =
                photoService.getPhotoById(id) ?: return ResponseEntity("No Photos with  Id $id", HttpStatus.NOT_FOUND)
            return ResponseEntity.ok(photos)
        } catch (e: FeignException) {
            return ResponseEntity("No Photos with Id $id", HttpStatus.NOT_FOUND)
        }
    }

    @Operation(summary = "Get Photos by album id")
    @GetMapping(value = ["/album/{albumId}"])
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved all photos by album id",
                content = [
                    Content(
                        mediaType = "application/json",
                        examples = [ExampleObject(
                            name = "Photo",
                            value = """
                                [
                                    {
                                        "id": 1,
                                        "albumId": 1,
                                        "title": "accusamus beatae ad facilis cum similique qui sunt",
                                        "url": "https://via.placeholder.com/600/92c952",
                                        "thumbnailurl": "https://via.placeholder.com/600/92c952"
                                    }
                                ]
                                """)])]
            ), ApiResponse(
                responseCode = "404",
                description = "No photo found")])
    fun getPhotoByAlbumId(@PathVariable albumId: String): ResponseEntity<Any> {
        val photos = photoService.getPhotoByAlbum(albumId)
        if (photos.isEmpty()) return ResponseEntity("No Photos with Album Id $albumId", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(photos)
    }

}