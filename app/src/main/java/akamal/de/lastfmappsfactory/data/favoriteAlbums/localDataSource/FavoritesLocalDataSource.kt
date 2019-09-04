package akamal.de.lastfmappsfactory.data.favoriteAlbums.localDataSource

import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.TopAlbumDetailsEntity
import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.TopAlbumsDetailsDao
import akamal.de.lastfmappsfactory.data.rx.RxCompletableCallback
import akamal.de.lastfmappsfactory.data.rx.RxSingleCallback
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoritesLocalDataSource @Inject constructor(private val dao: TopAlbumsDetailsDao) {

    fun getFavoriteAlbums():Single<List<TopAlbumDetailsEntity>> = dao.getAllAlbums().compose(RxSingleCallback.getSchedulersForSingle())
    fun removeSingleAlbum(album: TopAlbumDetailsEntity): Completable = dao.deleteSingleAlbum(album).compose(RxCompletableCallback.getSchedulersForCompletable())
}