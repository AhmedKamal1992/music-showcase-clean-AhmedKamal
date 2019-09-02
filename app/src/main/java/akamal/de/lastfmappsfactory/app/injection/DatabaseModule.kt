package akamal.de.lastfmappsfactory.app.injection

import akamal.de.lastfmappsfactory.app.database.ApplicationDatabase
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesAppDatabase(context: Context): ApplicationDatabase = ApplicationDatabase.getInstance(context)
}