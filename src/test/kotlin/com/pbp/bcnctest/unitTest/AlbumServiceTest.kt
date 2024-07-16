package com.pbp.bcnctest.unitTest

import com.pbp.bcnctest.models.Album
import com.pbp.bcnctest.models.Photo
import com.pbp.bcnctest.repository.AlbumRepository
import com.pbp.bcnctest.repository.PhotoRepository
import com.pbp.bcnctest.service.AlbumService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test
import kotlin.test.assertEquals
import org.assertj.core.api.Assertions.assertThat
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.mock.mockito.MockBean


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlbumServiceTest {

    @Autowired
    private lateinit var albumService: AlbumService

    @MockBean
    lateinit var albumRepository: AlbumRepository

    val album: Album = Album(id = 1, title = "title", userId = "1", null)
    val albumWithPhotos: Album = Album(
        id = 1, title = "title", userId = "1",
        listOf(Photo(id = 1, albumId = 11, title = "title", url = "url", thumbnailUrl = "thumbailUrl"))
    )

    @Test
    fun whenGetAlbum_thenReturnAlbum() {
        Mockito.`when`(albumRepository.getAllAlbums()).thenReturn(listOf(album))
        //when
        val result = albumService.getAlbums()
        //then
        assertThat(result[0]).isExactlyInstanceOf(Album::class.java)
    }

    @Test
    fun whenGetAlbumWithPhotoTrue_thenReturnAlbumWithPhotos() {
        Mockito.`when`(albumRepository.getAllAlbums()).thenReturn(listOf(albumWithPhotos))
        //when
        val result = albumService.getAlbumsWithPhotos(true)
        //then
        assertThat(result[0]).isExactlyInstanceOf(Album::class.java)
    }

    @Test
    fun whenGetAlbumWithPhotoFalse_thenReturnAlbumWithPhotos() {
        Mockito.`when`(albumRepository.getAllAlbums()).thenReturn(listOf(album))
        //when
        val result = albumService.getAlbumsWithPhotos(false)
        //then
        assertThat(result[0]).isExactlyInstanceOf(Album::class.java)
    }

    @Test
    fun whenGetAlbumWithId_thenReturnAlbum() {
        Mockito.`when`(albumRepository.getAlbumById("1")).thenReturn(album)
        //when
        val result = albumService.getAlbumById("1")
        //then
        assertThat(result).isExactlyInstanceOf(Album::class.java)
    }

    @Test
    fun whenGetAlbumWithId_thenNull() {
        Mockito.`when`(albumRepository.getAlbumById("aaa")).thenReturn(null)
        //when
        val result = albumService.getAlbumById("not id")
        //then
        assertEquals(result, null)
    }

    @Test
    fun whenGetAlbumWithUserId_thenReturnAlbum() {
        Mockito.`when`(albumRepository.getAlbumByUserId("1")).thenReturn(listOf(album))
        //when
        val result = albumService.getAlbumsByUserId("1")
        //then
        assertThat(result[0]).isExactlyInstanceOf(Album::class.java)
    }

    @Test
    fun whenGetAlbumWithUserId_thenNull() {
        Mockito.`when`(albumRepository.getAlbumByUserId("aaa")).thenReturn(null)
        //when
        val result = albumService.getAlbumById("not id")
        //then
        assertEquals(result, null)
    }

}