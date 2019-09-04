package akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource

import akamal.de.lastfmappsfactory.data.albumsDetails.model.TopAlbumDetails
import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TopAlbumsDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAlbum(album: TopAlbumDetails): Completable

    @Query("SELECT * FROM albums")
    fun getAllAlbums(): Single<List<TopAlbumDetails>>

    @Query("SELECT * FROM albums WHERE albumId = :Id")
    fun getSingleAlbum(Id: String): Single<TopAlbumDetails>

    @Delete
    fun deleteSingleAlbum(album: TopAlbumDetails): Completable
}