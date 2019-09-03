package akamal.de.lastfmappsfactory.data.topAlbums.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class AlbumImage(
    @SerializedName("#text")
    val imageUrl: String,
    val size: String
)