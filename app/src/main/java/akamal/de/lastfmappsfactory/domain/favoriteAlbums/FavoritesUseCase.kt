package akamal.de.lastfmappsfactory.domain.favoriteAlbums

import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.TopAlbumDetailsEntity
import akamal.de.lastfmappsfactory.data.common.DataResult
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbum
import io.reactivex.Single

interface FavoritesUseCase {
    fun getFavorites(): Single<DataResult<List<TopAlbum>>>
}