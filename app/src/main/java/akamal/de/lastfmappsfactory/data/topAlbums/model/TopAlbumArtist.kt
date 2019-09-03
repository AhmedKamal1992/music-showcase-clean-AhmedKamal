package akamal.de.lastfmappsfactory.data.topAlbums.model

import androidx.room.ColumnInfo

data class TopAlbumArtist(
    @ColumnInfo(name = "artistId")
    val mbid: String,
    @ColumnInfo(name = "artistTopAlbumName")
    val name: String
)