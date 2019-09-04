package akamal.de.lastfmappsfactory.app.database

import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.TopAlbumDetailsEntity
import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.TopAlbumsDetailsDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [TopAlbumDetailsEntity::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseTypeConverters::class)
abstract class ApplicationDatabase: RoomDatabase() {

    abstract fun topAlbumsDetailsDao(): TopAlbumsDetailsDao

    companion object {
        /**
         * The only instance
         */
        private var dbInstance: ApplicationDatabase? = null
        /**
         * Gets the singleton instance of SampleDatabase.
         *
         * @param context The context.
         * @return The singleton instance of SampleDatabase.
         */

        @Synchronized
        fun getInstance(context: Context): ApplicationDatabase {
            if(dbInstance == null) {
                dbInstance = Room.databaseBuilder(context.applicationContext, ApplicationDatabase::class.java, "LastFmDatabase").
                    fallbackToDestructiveMigration().
                    build()
            }
            return dbInstance!!
        }
    }



}