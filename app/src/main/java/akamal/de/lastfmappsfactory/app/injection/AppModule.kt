package akamal.de.lastfmappsfactory.app.injection

import akamal.de.lastfmappsfactory.app.application.AndroidApplication
import android.content.Context
import dagger.Module
import dagger.Provides
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class AppModule(val app: AndroidApplication) {

    @Singleton
    @Provides
    fun providesAppContext(): Context = app.applicationContext

    @Singleton
    @Provides
    fun provideAppExecutorService(): ExecutorService = Executors.newFixedThreadPool(10)
}