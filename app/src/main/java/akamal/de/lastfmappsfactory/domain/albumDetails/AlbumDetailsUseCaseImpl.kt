package akamal.de.lastfmappsfactory.domain.albumDetails

import akamal.de.lastfmappsfactory.data.albumsDetails.model.AlbumsDetailsResponse
import akamal.de.lastfmappsfactory.data.albumsDetails.model.TopAlbumDetails
import akamal.de.lastfmappsfactory.data.albumsDetails.repository.AlbumDetailsRepository
import akamal.de.lastfmappsfactory.data.common.DataResult
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AlbumDetailsUseCaseImpl @Inject constructor(private val repository: AlbumDetailsRepository): AlbumDetailsUseCase {
    override fun getAlbumDetails(albumId: String): Single<DataResult<AlbumsDetailsResponse>> = repository.getAlbumDetails(albumId)
    override fun saveSingleAlbum(album: TopAlbumDetails): Completable = repository.saveSingleAlbum(album)
    override fun removeSingleAlbum(album: TopAlbumDetails): Completable = repository.deleteSingleAlbum(album)
}