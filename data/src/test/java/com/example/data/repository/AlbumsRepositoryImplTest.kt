package com.example.data.repository

import com.example.data.FileUtil
import com.example.data.RetrofitTestUtil
import com.example.data.transformer.JsonAlbumsTransformer
import com.example.domain.Albums
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AlbumsRepositoryImplTest {

    private val server = MockWebServer()
    private lateinit var repository: AlbumsRepositoryImpl

    @Before
    fun setUp() {
        server.start()
        val adapter = RetrofitTestUtil.buildAdapter(server.url("/"))
        val service = adapter.create(AlbumsApiService::class.java)

        repository = AlbumsRepositoryImpl(service, JsonAlbumsTransformer())
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `fetchAlbums integration test on response code != 200`() = runBlocking {
        // Given
        val response = MockResponse()
        response.setResponseCode(404)
        server.enqueue(response)

        // When
        val albums = repository.fetchAlbums()

        // Then
        Assert.assertNull(albums)
    }

    @Test
    fun `fetchAlbums integration test on error`() = runBlocking {
        // Given
        val response = MockResponse()
        response.socketPolicy = SocketPolicy.DISCONNECT_AT_START
        server.enqueue(response)

        // When
        val albums = repository.fetchAlbums()

        // Then
        Assert.assertNull(albums)
    }

    @Test
    fun `fetchAlbums integration test`() = runBlocking {
        // Given
        server.enqueue(MockResponse().setBody(FileUtil.readFile("/json/albums.json") ?: ""))
        val expectedAlbums = listOf(
            Albums(
                albumsId = 1,
                id = 1,
                title = "accusamus",
                imageUrl = "link1",
                ThumbnailUrl = "thumbLink1"
            ),
            Albums(
                albumsId = 1,
                id = 2,
                title = "reprehenderit",
                imageUrl = "link2",
                ThumbnailUrl = "thumbLink2"
            )
        )

        // When
        val albums = repository.fetchAlbums()

        // Then
        Assert.assertNotNull(albums)
        Assert.assertEquals(
            expectedAlbums, albums
        )
    }
}