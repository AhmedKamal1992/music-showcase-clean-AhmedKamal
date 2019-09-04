package akamal.de.lastfmappsfactory.data.albumsDetails.model

data class AlbumsDetailsResponse(
    val album: TopAlbumDetails
) {
    var isFavorite = false
}