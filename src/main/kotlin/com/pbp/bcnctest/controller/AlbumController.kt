package com.pbp.bcnctest.controller


import com.pbp.bcnctest.service.AlbumService
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
@RequestMapping("/album")
@Tag(name = "Album", description = "Rest API for Albums")
class AlbumController @Autowired constructor(private val albumService: AlbumService) {

    @Operation(summary = "Get all albums")
    @GetMapping()
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved all albums",
                content = [
                    Content(
                        mediaType = "application/json",
                        examples = [ExampleObject(
                            name = "Album",
                            value = """
                                [
                                    {
                                        "id": 1,
                                        "title": "quidem molestiae enim",
                                        "userId": 1
                                    }
                                ]
                                """)])]),
            ApiResponse(
                responseCode = "404",
                description = "No albums found")])
    fun getAlbums(): ResponseEntity<Any> {
        return ResponseEntity.ok(albumService.getAlbums())
    }

    @Operation(summary = "Get all albums with photos")
    @GetMapping(value = ["/all"])
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved all albums with photos",
                content = [
                    Content(
                        mediaType = "application/json",
                        examples = [ExampleObject(
                            name = "Album",
                            value = """
                                [
                                    {
                                        "id": 1,
                                        "title": "quidem molestiae enim",
                                        "userId": 1,
                                        "photos": [
                                            {
                                                "id": 1,
                                                "albumId": 1,
                                                "title": "accusamus beatae ad facilis cum similique qui sunt",
                                                "url": "https://via.placeholder.com/600/92c952",
                                                "thumbnailurl": "https://via.placeholder.com/600/92c952"
                                            },
                                            {
                                                "id": 2,
                                                "albumId": 1,
                                                "title": "reprehenderit est deserunt velit ipsam",
                                                "url": "https://via.placeholder.com/600/771796",
                                                "thumbnailurl": "https://via.placeholder.com/600/771796"
                                            }
                                        ]
                                    }
                                ]
                                """)])]
            ),
            ApiResponse(
                responseCode = "404",
                description = "No albums found")])
    fun getAlbumsWithPhotos(): ResponseEntity<Any> {
        val albums = albumService.getAlbumsWithPhotos()
        if (albums.isEmpty()) return ResponseEntity("No albums found", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(albums)
    }

    @Operation(summary = "Get album by id")
    @GetMapping(value = ["/{id}"])
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved album by id",
                content = [
                    Content(
                        mediaType = "application/json",
                        examples = [ExampleObject(
                            name = "Album",
                            value = """
                                    {
                                        "id": 1,
                                        "title": "quidem molestiae enim",
                                        "userId": 1
                                    }
                                """)])]
            ), ApiResponse(
                responseCode = "404",
                description = "No albums found")])
    fun getAlbumById(@PathVariable(value = "id") id: String): ResponseEntity<Any> {
        try {
            val album = albumService.getAlbumById(id) ?: return ResponseEntity(
                "No album found with id: $id",
                HttpStatus.NOT_FOUND
            )
            return ResponseEntity.ok(album)
        } catch (e: FeignException) {
            return ResponseEntity("No albums with Id $id", HttpStatus.NOT_FOUND)
        }
    }

    @Operation(summary = "Get album by user id")
    @GetMapping(value = ["/user/{userId}"])
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved albums by user id",
                content = [
                    Content(
                        mediaType = "application/json",
                        examples = [ExampleObject(
                            name = "Album",
                            value = """
                                [
                                    {
                                        "id": 1,
                                        "title": "quidem molestiae enim",
                                        "userId": 1
                                    }
                                ]
                                """)])]),
            ApiResponse(
                responseCode = "404",
                description = "No albums found")])
    fun getAlbumsByUserId(@PathVariable userId: String): ResponseEntity<Any> {
        val albums = albumService.getAlbumsByUserId(userId)
        if (albums.isEmpty()) return ResponseEntity("No albums with User Id $userId", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(albums)
    }

}