package com.example.lbcchallenge

import android.app.Application
import com.example.lbcchallenge.presentation.di.components.ApplicationComponent
import com.example.lbcchallenge.presentation.di.components.DaggerApplicationComponent
import com.example.lbcchallenge.presentation.di.modules.ApplicationModule

class LbcChallengeApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(
            ApplicationModule(this)
        ).build()
    }
}