package akamal.de.lastfmappsfactory.data.albumsDetails.model

import akamal.de.lastfmappsfactory.data.topAlbums.model.AlbumImage
import androidx.room.*

@Entity(tableName = "albums")
data class TopAlbumDetails(
    @ColumnInfo(name = "artistName")
    val artist: String,
    @ColumnInfo(name = "image")
    val image: List<AlbumImage>,
    @ColumnInfo(name = "albumId")
    @PrimaryKey
    val mbid: String,
    @ColumnInfo(name = "albumName")
    val name: String,
    @ColumnInfo(name = "playCount")
    val playcount: String,
    @Embedded
    val tracks: Tracks,
    @Ignore
    var isFavorite:Boolean = false
)