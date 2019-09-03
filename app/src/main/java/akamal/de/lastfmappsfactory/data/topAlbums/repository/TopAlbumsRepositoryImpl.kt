package akamal.de.lastfmappsfactory.data.topAlbums.repository

import akamal.de.lastfmappsfactory.data.common.DataResult
import akamal.de.lastfmappsfactory.data.common.DataSource
import akamal.de.lastfmappsfactory.data.topAlbumsDetails.localDataSource.TopAlbumsDetailsLocalDataSource
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbum
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbumsResponse

import akamal.de.lastfmappsfactory.data.topAlbums.remoteDataSource.TopAlbumsRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class TopAlbumsRepositoryImpl @Inject constructor(private val remoteDataSource: TopAlbumsRemoteDataSource, private val localDataSource: TopAlbumsDetailsLocalDataSource): TopAlbumsRepository {
    override fun getTopAlbums(): Single<DataResult<TopAlbumsResponse>> = remoteDataSource.getTopAlbums().map { DataResult.Success(DataSource.Network, it) as DataResult<TopAlbumsResponse> }.onErrorReturn { DataResult.Error(DataSource.Network, it) }
}