package com.example.lbcchallenge.presentation.viewmodels.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.AlbumsRepository
import com.example.lbcchallenge.domain.DisplayableAlbum
import com.example.lbcchallenge.domain.mapper.DisplayableAlbumsTransformer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class AlbumsViewModel(
    private val albumsRepository: AlbumsRepository,
    private val transformer: DisplayableAlbumsTransformer,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _displayState: MutableLiveData<AlbumsDisplayState> by lazy {
        MutableLiveData<AlbumsDisplayState>().apply { getAlbums() }
    }

    val displayState: LiveData<AlbumsDisplayState>
        get() = _displayState

    fun getAlbums() = viewModelScope.launch(dispatcher) {
        _displayState.postValue(AlbumsDisplayState.Loading)
        val albumsList = albumsRepository.fetchAlbums()
        albumsList?.let { albums ->
            _displayState.postValue(
                AlbumsDisplayState.Success(albums = albums.map { album ->
                    transformer.transformAlbums(album)
                })
            )
        } ?: kotlin.run {
            _displayState.postValue(AlbumsDisplayState.Error)
        }
    }
}

sealed class AlbumsDisplayState {

    object Loading : AlbumsDisplayState()
    object Error : AlbumsDisplayState()
    data class Success(val albums: List<DisplayableAlbum>) : AlbumsDisplayState()
}
