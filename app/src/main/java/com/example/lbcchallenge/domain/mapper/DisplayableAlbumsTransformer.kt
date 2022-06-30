package com.example.lbcchallenge.domain.mapper

import com.example.domain.Albums
import com.example.lbcchallenge.domain.DisplayableAlbum
import javax.inject.Inject

class DisplayableAlbumsTransformer @Inject constructor() {
    fun transformAlbums(albums: Albums): DisplayableAlbum {
        return DisplayableAlbum(
            title = albums.title,
            imageUrl = albums.imageUrl,
            thumbnailUrl = albums.ThumbnailUrl
        )
    }
}