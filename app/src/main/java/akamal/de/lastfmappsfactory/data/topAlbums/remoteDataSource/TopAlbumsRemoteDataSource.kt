package akamal.de.lastfmappsfactory.data.topAlbums.remoteDataSource

import akamal.de.lastfmappsfactory.data.rx.RxSingleCallback
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbumsResponse
import io.reactivex.Single
import javax.inject.Inject

class TopAlbumsRemoteDataSource @Inject constructor(private val service: TopAlbumsService) {
    fun getTopAlbums(artistName: String): Single<TopAlbumsResponse> = service.getTopAlbums(artistName).compose(RxSingleCallback.getSchedulersForSingle())
}