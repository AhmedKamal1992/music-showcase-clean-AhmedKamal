package akamal.de.lastfmappsfactory.data.topAlbumsDetails.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverter
import com.google.gson.Gson

data class Tracks(
    @ColumnInfo(name = "tracks")
    val track: List<AblumTracks>) {
}