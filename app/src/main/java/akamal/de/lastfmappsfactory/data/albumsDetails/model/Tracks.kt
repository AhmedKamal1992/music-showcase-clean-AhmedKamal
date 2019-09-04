package akamal.de.lastfmappsfactory.data.albumsDetails.model

import androidx.room.ColumnInfo

data class Tracks(
    @ColumnInfo(name = "tracks")
    val track: List<AlbumTracks>) {
}