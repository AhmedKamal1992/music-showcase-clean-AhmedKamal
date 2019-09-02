package akamal.de.lastfmappsfactory.app.application

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class AndroidApplication: Application(), HasAndroidInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        setup()
    }

    private fun setup() {

    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}