package akamal.de.lastfmappsfactory.data.topAlbumsDetails.model

import akamal.de.lastfmappsfactory.data.topAlbums.model.AlbumImage
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

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
    val tracks: Tracks
)