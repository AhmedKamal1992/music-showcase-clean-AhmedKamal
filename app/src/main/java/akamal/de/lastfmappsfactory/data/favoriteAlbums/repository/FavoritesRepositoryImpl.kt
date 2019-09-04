package akamal.de.lastfmappsfactory.data.favoriteAlbums.repository

import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.TopAlbumDetailsEntity
import akamal.de.lastfmappsfactory.data.common.DataResult
import akamal.de.lastfmappsfactory.data.common.DataSource
import akamal.de.lastfmappsfactory.data.favoriteAlbums.localDataSource.FavoritesLocalDataSource
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(private val localDataSource: FavoritesLocalDataSource): FavoritesRepository {
    override fun getFavoriteAlbums(): Single<DataResult<List<TopAlbumDetailsEntity>>> =
        localDataSource.getFavoriteAlbums().map { DataResult.Success(DataSource.Local, it) as DataResult<List<TopAlbumDetailsEntity>>}.
            onErrorReturn { DataResult.Error(DataSource.Local, it) }

    override fun removeSingleAlbum(album: TopAlbumDetailsEntity): Completable = localDataSource.removeSingleAlbum(album)
}