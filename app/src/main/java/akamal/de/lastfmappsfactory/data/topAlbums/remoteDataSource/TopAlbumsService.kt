package akamal.de.lastfmappsfactory.data.topAlbums.remoteDataSource

import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbumsResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TopAlbumsService {
    @GET("/2.0/?method=artist.gettopalbums&artist={artist}&format=json")
    fun getTopAlbums(@Path("artist") artist: String): Single<TopAlbumsResponse>
}