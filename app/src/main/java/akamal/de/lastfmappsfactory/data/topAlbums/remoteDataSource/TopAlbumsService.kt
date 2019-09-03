package akamal.de.lastfmappsfactory.data.topAlbums.remoteDataSource

import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbumsResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TopAlbumsService {
    @GET("/2.0/?method=artist.gettopalbums&format=json")
    fun getTopAlbums(@Query("artist") artist: String): Single<TopAlbumsResponse>
}