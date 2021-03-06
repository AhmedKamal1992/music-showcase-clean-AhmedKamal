package akamal.de.lastfmappsfactory.data.favoriteAlbums.repository

import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.TopAlbumDetailsEntity
import akamal.de.lastfmappsfactory.data.common.DataResult
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbum
import io.reactivex.Completable
import io.reactivex.Single

interface FavoritesRepository {
    fun getFavoriteAlbums(): Single<DataResult<List<TopAlbum>>>
}