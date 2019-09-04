package akamal.de.lastfmappsfactory.data.albumsDetails.model

import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.TopAlbumDetailsEntity
import akamal.de.lastfmappsfactory.data.topAlbums.model.AlbumImage
import androidx.room.*

data class TopAlbumDetails(
    val mbid: String,
    val artist: String,
    val image: List<AlbumImage>,
    val name: String,
    val playcount: String,
    val tracks: Tracks
){
    var isFavorite: Boolean = false
}

fun TopAlbumDetails.toAlbumEntity(): TopAlbumDetailsEntity = TopAlbumDetailsEntity(mbid, artist, image, name, playcount, tracks)