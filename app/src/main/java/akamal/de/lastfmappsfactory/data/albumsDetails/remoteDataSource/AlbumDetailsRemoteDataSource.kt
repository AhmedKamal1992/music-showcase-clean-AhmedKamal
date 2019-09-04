package akamal.de.lastfmappsfactory.data.albumsDetails.remoteDataSource

import akamal.de.lastfmappsfactory.data.albumsDetails.model.AlbumsDetailsResponse
import akamal.de.lastfmappsfactory.data.rx.RxSingleCallback
import io.reactivex.Single
import javax.inject.Inject

class AlbumDetailsRemoteDataSource @Inject constructor(private val service: AlbumDetailsService) {
    fun getAlbumDetails(albumId: String): Single<AlbumsDetailsResponse> = service.getTopAlbums("disturbed", albumId).compose(RxSingleCallback.getSchedulersForSingle())
}