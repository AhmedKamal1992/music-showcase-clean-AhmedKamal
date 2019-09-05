package akamal.de.lastfmappsfactory.data.topAlbums.remoteDataSource

import akamal.de.lastfmappsfactory.data.rx.RxObservableCallback
import akamal.de.lastfmappsfactory.data.rx.RxSingleCallback
import javax.inject.Inject

class TopAlbumsRemoteDataSource @Inject constructor(private val service: TopAlbumsService) {

    fun getTopAlbums() = service.getTopAlbums("linkin park").compose(RxSingleCallback.getSchedulersForSingle())
}