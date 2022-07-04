package com.example.lbcchallenge.presentation.di.modules

import android.content.Context
import com.example.lbcchallenge.LbcChallengeApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: LbcChallengeApplication) {

    @Provides
    internal fun provideContext(): Context {
        return application.applicationContext
    }
}