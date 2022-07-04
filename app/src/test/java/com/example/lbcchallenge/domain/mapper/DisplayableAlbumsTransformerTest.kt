package com.example.lbcchallenge.domain.mapper

import com.example.domain.Albums
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DisplayableAlbumsTransformerTest {

    private lateinit var transformer: DisplayableAlbumsTransformer

    @Before
    fun setUp() {
        transformer = DisplayableAlbumsTransformer()
    }

    @Test
    fun `transformAlbum with a product`() {
        // Given
        val album = Albums(
            albumsId = 12345,
            id = 67890,
            title = "title",
            imageUrl = "url",
            ThumbnailUrl = "thumbnailUrl"
        )

        // When
        val displayableAlbum = transformer.transformAlbums(album)

        // Then
        assertEquals("title", displayableAlbum.title)
        assertEquals("url", displayableAlbum.imageUrl)
        assertEquals("thumbnailUrl", displayableAlbum.thumbnailUrl)
    }
}