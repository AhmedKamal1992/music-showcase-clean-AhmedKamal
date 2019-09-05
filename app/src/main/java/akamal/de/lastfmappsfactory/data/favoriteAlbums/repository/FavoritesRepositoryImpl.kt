package akamal.de.lastfmappsfactory.data.favoriteAlbums.repository

import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.TopAlbumDetailsEntity
import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.toTopAlbums
import akamal.de.lastfmappsfactory.data.common.DataResult
import akamal.de.lastfmappsfactory.data.common.DataSource
import akamal.de.lastfmappsfactory.data.favoriteAlbums.localDataSource.FavoritesLocalDataSource
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbum
import akamal.de.lastfmappsfactory.platform.extension.dropRequest
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(private val localDataSource: FavoritesLocalDataSource): FavoritesRepository {
    override fun getFavoriteAlbums(): Single<DataResult<List<TopAlbum>>> =
        localDataSource.getFavoriteAlbums().toObservable().flatMapIterable { it }.map { it.toTopAlbums() }.toList().map { DataResult.Success(DataSource.Local, it) as DataResult<List<TopAlbum>>}.
            onErrorReturn { DataResult.Error(DataSource.Local, it) }.dropRequest()
}