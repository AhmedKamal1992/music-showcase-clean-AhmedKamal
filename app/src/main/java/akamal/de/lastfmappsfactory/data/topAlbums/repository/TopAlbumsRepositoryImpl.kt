package akamal.de.lastfmappsfactory.data.topAlbums.repository

import akamal.de.lastfmappsfactory.data.common.DataResult
import akamal.de.lastfmappsfactory.data.common.DataSource
import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.AlbumsDetailsLocalDataSource
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbumsResponse

import akamal.de.lastfmappsfactory.data.topAlbums.remoteDataSource.TopAlbumsRemoteDataSource
import io.reactivex.Single
import javax.inject.Inject

class TopAlbumsRepositoryImpl @Inject constructor(private val remoteDataSource: TopAlbumsRemoteDataSource): TopAlbumsRepository {
    override fun getTopAlbums(artistName: String): Single<DataResult<TopAlbumsResponse>> =
        remoteDataSource.getTopAlbums(artistName).map { DataResult.Success(DataSource.Network, it) as DataResult<TopAlbumsResponse> }.
            onErrorReturn { DataResult.Error(DataSource.Network, it) }
}