package akamal.de.lastfmappsfactory.data.albumsDetails.repository

import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.AlbumsDetailsLocalDataSource
import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.TopAlbumDetailsEntity
import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.toAlbumDetails
import akamal.de.lastfmappsfactory.data.albumsDetails.model.AlbumsDetailsResponse
import akamal.de.lastfmappsfactory.data.albumsDetails.model.TopAlbumDetails
import akamal.de.lastfmappsfactory.data.albumsDetails.remoteDataSource.AlbumDetailsRemoteDataSource
import akamal.de.lastfmappsfactory.data.common.DataResult
import akamal.de.lastfmappsfactory.data.common.DataSource
import akamal.de.lastfmappsfactory.platform.extension.dropRequest
import androidx.core.text.HtmlCompat
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class AlbumDetailsRepositoryImpl @Inject constructor(private val remoteDataSource: AlbumDetailsRemoteDataSource, private val localDataSource: AlbumsDetailsLocalDataSource): AlbumDetailsRepository {
    override fun getAlbumDetails(albumId: String): Flowable<DataResult<AlbumsDetailsResponse>> {

        val remoteSource = Single.zip(localDataSource.getSingleAlbum(albumId).map { true }.onErrorReturn { false }, remoteDataSource.getAlbumDetails(albumId),
            BiFunction<Boolean, AlbumsDetailsResponse, DataResult<AlbumsDetailsResponse>> { isFavorite, response ->
                response.album.isFavorite = isFavorite
                return@BiFunction DataResult.Success(DataSource.Both, response) as DataResult<AlbumsDetailsResponse> }).
                onErrorReturn { DataResult.Error(DataSource.Network, it)}

        val localSource = localDataSource.getSingleAlbum(albumId).map { DataResult.Success(DataSource.Network,
            AlbumsDetailsResponse(it.toAlbumDetails())) as DataResult<AlbumsDetailsResponse> }.
            onErrorReturn { DataResult.Error(DataSource.Network, it) }.dropRequest()

            return Single.concatArrayEager(localSource, remoteSource)
    }


    override fun saveSingleAlbum(album: TopAlbumDetailsEntity): Completable = localDataSource.saveAlbum(album).dropRequest()
    override fun deleteSingleAlbum(album: TopAlbumDetailsEntity): Completable = localDataSource.deleteAlbum(album).dropRequest()
}