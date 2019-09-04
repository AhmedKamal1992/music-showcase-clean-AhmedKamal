package akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource

import akamal.de.lastfmappsfactory.data.albumsDetails.model.Artist
import akamal.de.lastfmappsfactory.data.albumsDetails.model.TopAlbumDetails
import akamal.de.lastfmappsfactory.data.albumsDetails.model.Tracks
import akamal.de.lastfmappsfactory.data.topAlbums.model.AlbumImage
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class TopAlbumDetailsEntity(
    @PrimaryKey
    @ColumnInfo(name = "albumId")
    val mbid: String,
    @ColumnInfo(name = "artistName")
    val artist: String,
    @ColumnInfo(name = "image")
    val image: List<AlbumImage>,
    @ColumnInfo(name = "albumName")
    val name: String,
    @ColumnInfo(name = "playCount")
    val playcount: String,
    @Embedded
    val tracks: Tracks
)

fun TopAlbumDetailsEntity.toAlbumDetails(): TopAlbumDetails = TopAlbumDetails(mbid, artist, image, name, playcount, tracks)