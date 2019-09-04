package akamal.de.lastfmappsfactory.data.albumsDetails.model

import androidx.room.ColumnInfo

data class Artist(
    @ColumnInfo(name = "mbid")
    val mbid: String,
    @ColumnInfo(name = "artistName")
    val name: String,
    @ColumnInfo(name = "artistUrl")
    val url: String
)