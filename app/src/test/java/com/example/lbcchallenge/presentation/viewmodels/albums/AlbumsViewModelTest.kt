package com.example.lbcchallenge.presentation.viewmodels.albums

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.domain.Albums
import com.example.domain.repository.AlbumsRepository
import com.example.lbcchallenge.domain.DisplayableAlbum
import com.example.lbcchallenge.domain.mapper.DisplayableAlbumsTransformer
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.then
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: AlbumsRepository

    @Mock
    private lateinit var transformer: DisplayableAlbumsTransformer

    @Mock
    private lateinit var observer: Observer<AlbumsDisplayState>

    private lateinit var viewModel: AlbumsViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        val dispatcher = Dispatchers.Unconfined
        viewModel = AlbumsViewModel(repository, transformer, dispatcher)
        Dispatchers.setMain(dispatcher)
        viewModel.displayState.observeForever(observer)
    }

    @Test
    fun `getAlbums when repository return null present error`() = runBlocking {
        // Given
        given(repository.fetchAlbums()).willReturn(null)

        // When
        viewModel.getAlbums()

        // Then
        inOrder(observer) {
            then(observer).should(this).onChanged(AlbumsDisplayState.Loading)
            then(observer).should(this).onChanged(AlbumsDisplayState.Error)
        }
    }

    @Test
    fun `getAlbums when repository return a list of album present success`() = runBlocking {
        // Given
        val album = mock<Albums>()
        val displayableAlbum = mock<DisplayableAlbum>()
        given(repository.fetchAlbums()).willReturn(listOf(album))
        given(transformer.transformAlbums(album)).willReturn(displayableAlbum)

        // When
        viewModel.getAlbums()

        // Then
        inOrder(observer) {
            then(observer).should(this).onChanged(AlbumsDisplayState.Loading)
            then(observer).should(this)
                .onChanged(AlbumsDisplayState.Success(listOf(displayableAlbum)))
        }

    }
}