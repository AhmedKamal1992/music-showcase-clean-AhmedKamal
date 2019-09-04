package akamal.de.lastfmappsfactory.domain.albumDetails

import akamal.de.lastfmappsfactory.data.albumsDetails.model.AlbumsDetailsResponse
import akamal.de.lastfmappsfactory.data.albumsDetails.model.TopAlbumDetails
import akamal.de.lastfmappsfactory.data.common.DataResult
import io.reactivex.Completable
import io.reactivex.Single

interface AlbumDetailsUseCase {
    fun getAlbumDetails(albumId: String): Single<DataResult<AlbumsDetailsResponse>>
    fun saveSingleAlbum(album: TopAlbumDetails): Completable
    fun removeSingleAlbum(album: TopAlbumDetails): Completable
}