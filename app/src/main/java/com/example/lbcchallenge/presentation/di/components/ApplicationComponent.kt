package com.example.lbcchallenge.presentation.di.components

import android.content.Context
import com.example.lbcchallenge.presentation.di.modules.ApplicationModule
import com.example.lbcchallenge.presentation.di.modules.ServiceModule
import dagger.Component

@Component(modules = [ApplicationModule::class, ServiceModule::class])
interface ApplicationComponent {
    fun context(): Context
}