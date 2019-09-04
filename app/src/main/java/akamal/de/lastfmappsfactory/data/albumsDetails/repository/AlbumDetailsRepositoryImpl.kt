package akamal.de.lastfmappsfactory.data.albumsDetails.repository

import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.AlbumsDetailsLocalDataSource
import akamal.de.lastfmappsfactory.data.albumsDetails.model.AlbumsDetailsResponse
import akamal.de.lastfmappsfactory.data.albumsDetails.model.TopAlbumDetails
import akamal.de.lastfmappsfactory.data.albumsDetails.remoteDataSource.AlbumDetailsRemoteDataSource
import akamal.de.lastfmappsfactory.data.common.DataResult
import akamal.de.lastfmappsfactory.data.common.DataSource
import androidx.core.text.HtmlCompat
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class AlbumDetailsRepositoryImpl @Inject constructor(private val remoteDataSource: AlbumDetailsRemoteDataSource, private val localDataSource: AlbumsDetailsLocalDataSource): AlbumDetailsRepository {
    override fun getAlbumDetails(albumId: String): Single<DataResult<AlbumsDetailsResponse>> =
        Single.zip(localDataSource.getSingleAlbum(albumId).map { true }.onErrorReturn { false }, remoteDataSource.getAlbumDetails(albumId),
            BiFunction<Boolean, AlbumsDetailsResponse, DataResult<AlbumsDetailsResponse>> { isFavorite, response ->
                response.album.isFavorite = isFavorite
                return@BiFunction DataResult.Success(DataSource.Both, response) as DataResult<AlbumsDetailsResponse> }).onErrorReturn { DataResult.Error(DataSource.Network, it)}


    override fun saveSingleAlbum(album: TopAlbumDetails): Completable = localDataSource.saveAlbum(album)
    override fun deleteSingleAlbum(album: TopAlbumDetails): Completable = localDataSource.deleteAlbum(album)
}