package com.example.data.repository

import com.example.data.model.JsonAlbums
import com.example.data.transformer.JsonAlbumsTransformer
import com.example.domain.Albums
import com.example.domain.repository.AlbumsRepository
import retrofit2.http.GET
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val api: AlbumsApiService,
    private val transformer: JsonAlbumsTransformer
) : AlbumsRepository {
    override suspend fun fetchAlbums(): List<Albums>? {
        return try {
            val jsonAlbums = api.fetchAlbums()
            transformer.getAlbums(jsonAlbums)
        } catch (e: Exception) {
            null
        }
    }
}

interface AlbumsApiService {
    @GET("/img/shared/technical-test.json")
    suspend fun fetchAlbums(): List<JsonAlbums>
}