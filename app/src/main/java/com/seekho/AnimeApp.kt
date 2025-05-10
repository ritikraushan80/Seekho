package com.seekho

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by Ritik on: 10/05/25
 */

@HiltAndroidApp
class AnimeApp : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}