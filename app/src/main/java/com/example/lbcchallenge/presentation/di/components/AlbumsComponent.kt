package com.example.lbcchallenge.presentation.di.components

import com.example.lbcchallenge.presentation.activity.albums.AlbumsActivity
import com.example.lbcchallenge.presentation.di.modules.AlbumsModule
import com.example.lbcchallenge.presentation.di.modules.ServiceModule
import dagger.Component

@Component(
    dependencies = [ApplicationComponent::class],
    modules = [AlbumsModule::class, ServiceModule::class]
)
interface AlbumsComponent {
    fun inject(activity: AlbumsActivity)
}