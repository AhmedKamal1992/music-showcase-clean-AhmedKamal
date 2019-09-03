package akamal.de.lastfmappsfactory.app.application

import akamal.de.lastfmappsfactory.BuildConfig
import akamal.de.lastfmappsfactory.app.injection.AppComponent
import akamal.de.lastfmappsfactory.app.injection.AppModule
import akamal.de.lastfmappsfactory.app.injection.DaggerAppComponent
import akamal.de.lastfmappsfactory.app.injection.NetworkModule
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

class AndroidApplication: Application(), HasAndroidInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        setup()
    }

    private fun setup() {
        if(BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder().
            application(this).
            appModule(AppModule(this)).
            networkModule(NetworkModule()).inject()

        appComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    companion object {
        lateinit var appComponent: AppComponent private set
    }
}