package com.pbp.bcnctest.integrationTest

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.pbp.bcnctest.models.Album
import com.pbp.bcnctest.models.Photo
import com.pbp.bcnctest.repository.AlbumRepository
import com.pbp.bcnctest.repository.PhotoRepository
import com.pbp.bcnctest.service.AlbumService
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest
class AlbumControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc
    val album: Album = Album(id = 1, title = "title", userId = "1", null)
    val albumWithPhotos: Album = Album(
        id = 1, title = "title", userId = "1",
        listOf(Photo(id = 1, albumId = 11, title = "title", url = "url", thumbnailUrl = "thumbailUrl"))
    )

    @MockBean
    private lateinit var albumService: AlbumService

    @MockBean
    private lateinit var albumRepository: AlbumRepository
    val mapper = jacksonObjectMapper()

    @Test
    fun getAlbumStatusSuccess() {
        //when
        Mockito.`when`(albumService.getAlbums()).thenReturn(listOf(album))

        val result = mockMvc.perform(MockMvcRequestBuilders.get("/album")).andExpect(status().isOk).andExpect(
            content().contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response.contentAsString

        val albumResult = mapper.readValue(result, Array<Album>::class.java)
        assertArrayEquals(arrayOf(album), albumResult)
    }

    @Test
    fun getAlbumWithPhotoStatusSuccess() {
        Mockito.`when`(albumService.getAlbumsWithPhotos(true)).thenReturn(listOf(albumWithPhotos))

        val result = mockMvc.perform(MockMvcRequestBuilders.get("/album/all/true")).andExpect(status().isOk).andExpect(
            content().contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response.contentAsString

        val albumResult = mapper.readValue(result, Array<Album>::class.java)
        assertArrayEquals(arrayOf(albumWithPhotos), albumResult)
    }

    @Test
    fun getAlbumWithPhotoStatus404() {
        Mockito.`when`(albumService.getAlbums()).thenReturn(emptyList())
        mockMvc.perform(MockMvcRequestBuilders.get("/album/all/true")).andExpect(status().isNotFound)
    }

    @Test
    fun getAlbumWithIdStatusSuccess() {
        Mockito.`when`(albumService.getAlbumById("1")).thenReturn(album)

        val result = mockMvc.perform(MockMvcRequestBuilders.get("/album/1")).andExpect(status().isOk).andExpect(
            content().contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response.contentAsString

        val albumResult = mapper.readValue(result, Album::class.java)
        assertEquals(album, albumResult)
    }

    @Test
    fun getAlbumWithIdStatus404() {
        mockMvc.perform(MockMvcRequestBuilders.get("/album/aaa")).andExpect(status().isNotFound)
    }

    @Test
    fun getAlbumWithUserIdStatusSuccess() {
        Mockito.`when`(albumService.getAlbumsByUserId("1")).thenReturn(listOf(album))

        val result = mockMvc.perform(MockMvcRequestBuilders.get("/album/user/1")).andExpect(status().isOk).andExpect(
            content().contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response.contentAsString

        val albumResult = mapper.readValue(result, Array<Album>::class.java)
        assertArrayEquals(arrayOf(album), albumResult)
    }

    @Test
    fun getAlbumWithUserIdStatus404() {
        mockMvc.perform(MockMvcRequestBuilders.get("/album/user/aaa")).andExpect(status().isNotFound)
    }
}