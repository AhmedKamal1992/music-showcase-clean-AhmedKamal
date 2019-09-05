package akamal.de.lastfmappsfactory.domain.favoriteAlbums

import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.TopAlbumDetailsEntity
import akamal.de.lastfmappsfactory.data.common.DataResult
import akamal.de.lastfmappsfactory.data.favoriteAlbums.repository.FavoritesRepository
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbum
import io.reactivex.Single
import javax.inject.Inject

class FavoritesUseCaseImpl @Inject constructor(private val repository: FavoritesRepository): FavoritesUseCase {
    override fun getFavorites(): Single<DataResult<List<TopAlbum>>> = repository.getFavoriteAlbums()
}