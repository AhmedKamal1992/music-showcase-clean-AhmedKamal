package akamal.de.lastfmappsfactory.app.database

import akamal.de.lastfmappsfactory.data.topAlbums.model.AlbumImage
import akamal.de.lastfmappsfactory.data.albumsDetails.model.AlbumTracks
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class DatabaseTypeConverters {

    @TypeConverter
    fun convertFromImage(value: List<AlbumImage>?): String? {
        return if (value == null) null else Gson().toJson(value)
    }

    @TypeConverter
    fun convertToImage(value: String?): List<AlbumImage>? {
        return Gson().fromJson(value, object : TypeToken<List<AlbumImage>>() {}.type)
    }

    @TypeConverter
    fun convertFromTrackList(value: List<AlbumTracks>): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun convertToTrackList(value: String): List<AlbumTracks>? {
        return Gson().fromJson(value, object : TypeToken<List<AlbumTracks>>() {}.type)
    }
}