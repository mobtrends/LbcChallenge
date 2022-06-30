package com.example.domain.repository

import com.example.domain.Albums

interface AlbumsRepository {
    suspend fun fetchAlbums(): List<Albums>?
}