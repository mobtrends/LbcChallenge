package com.example.data.transformer

import com.example.data.model.JsonAlbums
import com.example.domain.Albums
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class JsonAlbumsTransformerTest {

    private val transformer = JsonAlbumsTransformer()

    @Test
    fun `getAlbums when albums items are empty`() {
        // When
        val albumsList = transformer.getAlbums(emptyList())

        // Then
        assertNull(albumsList)
    }

    @Test
    fun `getAlbums when albums items are not empty`() {
        // Given
        val jsonAlbums = listOf(
            JsonAlbums(
                albumId = 12345,
                id = 67890,
                title = "title",
                url = "url",
                thumbnailUrl = "thumbnailUrl"
            )
        )

        val expectedAlbums = listOf(
            Albums(
                albumsId = 12345,
                id = 67890,
                title = "title",
                imageUrl = "url",
                ThumbnailUrl = "thumbnailUrl"
            )
        )

        // When
        val albumsList = transformer.getAlbums(jsonAlbums = jsonAlbums)

        // Then
        assertEquals(expectedAlbums, albumsList)
    }
}