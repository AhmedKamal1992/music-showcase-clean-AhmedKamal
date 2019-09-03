package akamal.de.lastfmappsfactory.data.topAlbums.repository

import akamal.de.lastfmappsfactory.data.common.DataResult
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbum
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbumsResponse
import io.reactivex.Completable
import io.reactivex.Single

interface TopAlbumsRepository {
    fun getTopAlbums(): Single<DataResult<TopAlbumsResponse>>
}