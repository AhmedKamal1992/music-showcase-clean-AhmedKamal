package akamal.de.lastfmappsfactory.data.albumsDetails.remoteDataSource

import akamal.de.lastfmappsfactory.data.albumsDetails.model.AlbumsDetailsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumDetailsService {
    @GET("/2.0/?method=album.getinfo&format=json")
    fun getTopAlbums(@Query("artist") artistId: String, @Query("mbid") albumId: String): Single<AlbumsDetailsResponse>
}