package akamal.de.lastfmappsfactory.data.topAlbums.repository

import akamal.de.lastfmappsfactory.data.common.DataResult
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbumsResponse
import io.reactivex.Single

interface TopAlbumsRepository {
    fun getTopAlbums(artistName: String): Single<DataResult<TopAlbumsResponse>>
}