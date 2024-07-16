package com.pbp.bcnctest.unitTest



import com.pbp.bcnctest.models.Photo
import com.pbp.bcnctest.repository.PhotoRepository
import com.pbp.bcnctest.service.PhotoService
import org.assertj.core.api.Assertions.assertThat
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PhotoServiceTest {

    @Autowired
    private lateinit var photoService: PhotoService

    @MockBean
    private lateinit var photoRepository: PhotoRepository

    val photo: Photo = Photo(id = 1, title = "title", url = "url", thumbnailUrl = "thumbnailulr", albumId = 11)

    @Test
    fun whenGetPhoto_thenReturnPhoto() {
        Mockito.`when`(photoRepository.getAllPhotos()).thenReturn(listOf(photo))
        //when
        val result = photoService.getPhotos()
        //then
        assertThat(result[0]).isExactlyInstanceOf(Photo::class.java)
    }


    @Test
    fun whenGetPhotoByAlbum_thenReturnPhoto() {
        Mockito.`when`(photoRepository.getPhotosByAlbumId("1")).thenReturn(listOf(photo))
        //when
        val result = photoService.getPhotoByAlbum("1")
        //then
        assertThat(result[0]).isExactlyInstanceOf(Photo::class.java)
    }

    @Test
    fun whenGetPhotoByAlbumId_thenReturnEmptyList() {
        //when
        val result = photoService.getPhotoByAlbum("not id")
        //then
        assertEquals(result, emptyList())
    }

    @Test
    fun whenGetPhotoById_thenReturnPhoto() {
        Mockito.`when`(photoRepository.getPhotoById("1")).thenReturn(photo)
        //when
        val result = photoService.getPhotoById("1")
        //then
        assertThat(result).isExactlyInstanceOf(Photo::class.java)
    }

    @Test
    fun whenGetPhotoById_thenReturnNull() {
        Mockito.`when`(photoRepository.getPhotoById("aaa")).thenReturn(null)
        //when
        val result = photoService.getPhotoById("aaa")
        //then
        assertEquals(result, null)
    }

}