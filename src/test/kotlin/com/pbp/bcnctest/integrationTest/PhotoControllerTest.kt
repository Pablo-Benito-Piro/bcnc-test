package com.pbp.bcnctest.integrationTest


import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.pbp.bcnctest.models.Photo
import com.pbp.bcnctest.repository.PhotoRepository
import com.pbp.bcnctest.service.PhotoService
import feign.FeignException
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

import kotlin.test.assertEquals


@AutoConfigureMockMvc
@SpringBootTest
class PhotoControllerTest(@Autowired val mockMvc: MockMvc) {

    val photo: Photo = Photo(id = 1, title = "title", url = "url", thumbnailUrl = "thumbnailulr", albumId = 11)

    @MockBean
    lateinit var photoService: PhotoService

    @MockBean
    lateinit var photoRepository: PhotoRepository

    val mapper = jacksonObjectMapper()

    @Test
    fun getPhotoStatusSuccess() {
        Mockito.`when`(photoService.getPhotos()).thenReturn(listOf(photo))

        val result = mockMvc.perform(MockMvcRequestBuilders.get("/photo")).andExpect(status().isOk).andExpect(
            content().contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response.contentAsString

        val photosResult = mapper.readValue(result, Array<Photo>::class.java)
        assertArrayEquals(arrayOf(photo), photosResult)

    }

    @Test
    fun getPhotoWithIdStatusSuccess() {
        Mockito.`when`(photoService.getPhotoById("1")).thenReturn(photo)

        val result = mockMvc.perform(MockMvcRequestBuilders.get("/photo/1")).andExpect(status().isOk).andExpect(
            content().contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response.contentAsString

        val photosResult = mapper.readValue(result, Photo::class.java)
        assertEquals(photo, photosResult)

    }

    @Test
    fun getPhotoWithIdStatus404() {
        mockMvc.perform(MockMvcRequestBuilders.get("/photo/aaa")).andExpect(status().isNotFound)
    }

    @Test
    fun getPhotoWithIdStatus404AndFeignException() {
        Mockito.`when`(photoService.getPhotoById("aaa")).thenThrow(FeignException::class.java)
        mockMvc.perform(MockMvcRequestBuilders.get("/photo/aaa")).andExpect(status().isNotFound)
    }

    @Test
    fun getPhotoWithAlbumIdStatusSuccess() {
        Mockito.`when`(photoService.getPhotoByAlbum("1")).thenReturn(listOf(photo))
        Mockito.`when`(photoRepository.getPhotosByAlbumId("1")).thenReturn(listOf(photo))
        val result =
            mockMvc.perform(MockMvcRequestBuilders.get("/photo/album/1")).andExpect(status().isOk).andExpect(
                content().contentType(MediaType.APPLICATION_JSON)
            ).andReturn().response.contentAsString

        val photosResult = mapper.readValue(result, Array<Photo>::class.java)
        assertArrayEquals(arrayOf(photo), photosResult)

    }

    @Test
    fun getPhotoWithAlbumIdStatus404() {
        mockMvc.perform(MockMvcRequestBuilders.get("/photo/album/aaa")).andExpect(status().isNotFound)
    }
}