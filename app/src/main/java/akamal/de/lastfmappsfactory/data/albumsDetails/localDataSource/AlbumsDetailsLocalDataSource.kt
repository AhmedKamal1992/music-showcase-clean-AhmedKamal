package akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource

import akamal.de.lastfmappsfactory.data.rx.RxCompletableCallback
import akamal.de.lastfmappsfactory.data.albumsDetails.model.TopAlbumDetails
import akamal.de.lastfmappsfactory.data.rx.RxSingleCallback
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AlbumsDetailsLocalDataSource @Inject constructor(private val dao: TopAlbumsDetailsDao) {
    fun saveAlbum(album: TopAlbumDetails): Completable = dao.saveAlbum(album).compose(RxCompletableCallback.getSchedulersForCompletable())
    fun deleteAlbum(album: TopAlbumDetails): Completable = dao.saveAlbum(album).compose(RxCompletableCallback.getSchedulersForCompletable())
    fun getSingleAlbum(albumId: String): Single<TopAlbumDetails> = dao.getSingleAlbum(albumId).compose(RxSingleCallback.getSchedulersForSingle())
}