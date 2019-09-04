package akamal.de.lastfmappsfactory.data.albumsDetails.repository

import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.TopAlbumDetailsEntity
import akamal.de.lastfmappsfactory.data.albumsDetails.model.AlbumsDetailsResponse
import akamal.de.lastfmappsfactory.data.albumsDetails.model.TopAlbumDetails
import akamal.de.lastfmappsfactory.data.common.DataResult
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface AlbumDetailsRepository {
    fun getAlbumDetails(albumId: String): Flowable<DataResult<AlbumsDetailsResponse>>
    fun saveSingleAlbum(album: TopAlbumDetailsEntity): Completable
    fun deleteSingleAlbum(album: TopAlbumDetailsEntity): Completable
}