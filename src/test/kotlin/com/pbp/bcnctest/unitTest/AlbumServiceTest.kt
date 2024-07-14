package com.pbp.bcnctest.unitTest

import com.pbp.bcnctest.models.Album
import com.pbp.bcnctest.service.AlbumService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test
import kotlin.test.assertEquals
import org.assertj.core.api.Assertions.assertThat


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlbumServiceTest {

    @Autowired
    private lateinit var albumService: AlbumService

    @Test
    fun whenGetAlbum_thenReturnAlbum() {
        //when
        val result = albumService.getAlbums()
        //then
        assertThat(result[0]).isExactlyInstanceOf(Album::class.java)
    }

    @Test
    fun whenGetAlbumWithPhotoTrue_thenReturnAlbumWithPhotos() {
        //when
        val result = albumService.getAlbumsWithPhotos(true)
        //then
        assertThat(result[0]).isExactlyInstanceOf(Album::class.java)
    }

    @Test
    fun whenGetAlbumWithPhotoFalse_thenReturnAlbumWithPhotos() {
        //when
        val result = albumService.getAlbumsWithPhotos(false)
        //then
        assertThat(result[0]).isExactlyInstanceOf(Album::class.java)
    }

    @Test
    fun whenGetAlbumWithId_thenReturnAlbum() {
        //when
        val result = albumService.getAlbumById("1")
        //then
        assertThat(result).isExactlyInstanceOf(Album::class.java)
    }

    @Test
    fun whenGetAlbumWithId_thenNull() {
        //when
        val result = albumService.getAlbumById("not id")
        //then
        assertEquals(result, null)
    }

    @Test
    fun whenGetAlbumWithUserId_thenReturnAlbum() {
        //when
        val result = albumService.getAlbumsByUserId("1")
        //then
        assertThat(result[0]).isExactlyInstanceOf(Album::class.java)
    }

    @Test
    fun whenGetAlbumWithUserId_thenNull() {
        //when
        val result = albumService.getAlbumById("not id")
        //then
        assertEquals(result, null)
    }

}