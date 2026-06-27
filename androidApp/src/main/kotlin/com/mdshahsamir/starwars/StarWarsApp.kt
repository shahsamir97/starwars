package com.mdshahsamir.starwars

import android.app.Application
import com.mdshahsamir.starwars.di.initKoin
import org.koin.android.ext.koin.androidContext

class StarWarsApp: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@StarWarsApp)
        }
    }
}