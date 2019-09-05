package akamal.de.lastfmappsfactory.data.topAlbums.model

data class TopAlbum(
    val mbid: String,
    val name: String,
    val image: List<AlbumImage>,
    val playcount: Int
)