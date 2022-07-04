package com.example.lbcchallenge.presentation.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repository.AlbumsApiService
import com.example.data.repository.AlbumsRepositoryImpl
import com.example.domain.repository.AlbumsRepository
import com.example.lbcchallenge.domain.mapper.DisplayableAlbumsTransformer
import com.example.lbcchallenge.presentation.activity.albums.AlbumsActivity
import com.example.lbcchallenge.presentation.viewmodels.albums.AlbumsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import javax.inject.Inject

@Module(includes = [AlbumsModule.BindingModule::class])
class AlbumsModule(private val activity: AlbumsActivity) {

    @Provides
    fun provideService(retrofit: Retrofit): AlbumsApiService =
        retrofit.create(AlbumsApiService::class.java)

    @Module
    internal abstract class BindingModule {
        @Binds
        abstract fun bindRepository(repository: AlbumsRepositoryImpl): AlbumsRepository
    }

    @Provides
    fun provideViewModel(factory: AlbumsViewModelFactory): AlbumsViewModel {
        return ViewModelProvider(activity, factory)[AlbumsViewModel::class.java]
    }
}

class AlbumsViewModelFactory @Inject constructor(
    private val albumsRepository: AlbumsRepository,
    private val transformer: DisplayableAlbumsTransformer,
    private val dispatcher: CoroutineDispatcher
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumsViewModel(
            albumsRepository,
            transformer,
            dispatcher
        ) as T
    }
}