package akamal.de.lastfmappsfactory.data.albumsDetails.model

import androidx.databinding.ObservableField

data class AlbumTracks(
    val artist: Artist,
    val duration: String,
    val name: String,
    val url: String
) {
    val durationDivided: String
        get() = "${duration.toInt()/60}:${duration.toInt().rem(60)}"
}