package akamal.de.lastfmappsfactory.domain.topAlbums

import akamal.de.lastfmappsfactory.data.common.DataResult
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbumsResponse
import io.reactivex.Single

interface TopAlbumsUseCase {
    fun getAllAlbums(): Single<DataResult<TopAlbumsResponse>>
}