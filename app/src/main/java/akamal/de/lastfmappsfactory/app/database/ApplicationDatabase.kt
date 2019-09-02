package akamal.de.lastfmappsfactory.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [], version = 1, exportSchema = false)
abstract class ApplicationDatabase: RoomDatabase() {


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