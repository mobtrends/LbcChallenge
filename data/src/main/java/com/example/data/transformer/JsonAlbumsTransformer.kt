package com.example.data.transformer

import com.example.data.model.JsonAlbums
import com.example.domain.Albums
import javax.inject.Inject

class JsonAlbumsTransformer @Inject constructor() {

    fun getAlbums(jsonAlbums: List<JsonAlbums>): List<Albums>? =
        jsonAlbums.map { jsonItem ->
            Albums(
                jsonItem.albumId,
                jsonItem.id,
                jsonItem.title,
                jsonItem.url,
                jsonItem.thumbnailUrl
            )
        }.ifEmpty { null }
}