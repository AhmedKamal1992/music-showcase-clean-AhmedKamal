package akamal.de.lastfmappsfactory.domain.topAlbums

import akamal.de.lastfmappsfactory.data.common.DataResult
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbumsResponse
import akamal.de.lastfmappsfactory.data.topAlbums.repository.TopAlbumsRepository
import io.reactivex.Single
import javax.inject.Inject

class TopAlbumsUseCaseImpl @Inject constructor(private val repository: TopAlbumsRepository): TopAlbumsUseCase {
    override fun getAllAlbums(artistName: String): Single<DataResult<TopAlbumsResponse>> = repository.getTopAlbums(artistName)
}