package akamal.de.lastfmappsfactory.app.database

import akamal.de.lastfmappsfactory.data.topAlbums.model.AlbumImage
import akamal.de.lastfmappsfactory.data.albumsDetails.model.AlbumTracks
import androidx.room.TypeConverter
import com.google.gson.Gson

class DatabaseTypeConverters {

    @TypeConverter
    fun convertFromImage(value: List<AlbumImage>?): String? {
        return if (value == null) null else Gson().toJson(value)
    }

    @TypeConverter
    fun convertToImage(value: String?): List<AlbumImage>? {
        return (if (value == null) null else Gson().fromJson(value, listOf<AlbumImage>()::class.java))
    }

    @TypeConverter
    fun convertFromTrackList(value: List<AlbumTracks>): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun convertToTrackList(value: String): List<AlbumTracks>? {
        return Gson().fromJson(value, listOf<AlbumTracks>()::class.java)
    }
}