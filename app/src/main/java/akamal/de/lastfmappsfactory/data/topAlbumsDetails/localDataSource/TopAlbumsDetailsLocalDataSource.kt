package akamal.de.lastfmappsfactory.data.topAlbumsDetails.localDataSource

import akamal.de.lastfmappsfactory.data.rx.RxCompletableCallback
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbum
import akamal.de.lastfmappsfactory.data.topAlbumsDetails.model.TopAlbumDetails
import io.reactivex.Completable
import javax.inject.Inject

class TopAlbumsDetailsLocalDataSource @Inject constructor(private val dao: TopAlbumsDetailsDao) {
    fun saveAlbum(album: TopAlbumDetails): Completable = dao.saveAlbum(album).compose(RxCompletableCallback.getSchedulersForCompletable())
}