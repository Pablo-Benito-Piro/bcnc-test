package com.pbp.bcnctest.unitTest



import com.pbp.bcnctest.models.Photo
import com.pbp.bcnctest.service.PhotoService
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PhotoServiceTest {

    @Autowired
    private lateinit var photoService: PhotoService

    @Test
    fun whenGetPhoto_thenReturnPhoto() {
        //when
        val result = photoService.getPhotos()
        //then
        assertThat(result[0]).isExactlyInstanceOf(Photo::class.java)
    }


    @Test
    fun whenGetPhotoByAlbum_thenReturnPhoto() {
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
        //when
        val result = photoService.getPhotoById("1")
        //then
        assertThat(result).isExactlyInstanceOf(Photo::class.java)
    }

    @Test
    fun whenGetPhotoById_thenReturnNull() {
        //when
        val result = photoService.getPhotoById("not id")
        //then
        assertEquals(result, null)
    }

}