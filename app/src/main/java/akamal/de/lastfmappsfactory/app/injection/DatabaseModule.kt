package akamal.de.lastfmappsfactory.app.injection

import akamal.de.lastfmappsfactory.app.database.ApplicationDatabase
import akamal.de.lastfmappsfactory.data.topAlbumsDetails.localDataSource.TopAlbumsDetailsDao
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesAppDatabase(context: Context): ApplicationDatabase = ApplicationDatabase.getInstance(context)

    @Singleton
    @Provides
    fun providesTopAlbumDetailsModule(database: ApplicationDatabase): TopAlbumsDetailsDao = database.topAlbumsDetailsDao()
}