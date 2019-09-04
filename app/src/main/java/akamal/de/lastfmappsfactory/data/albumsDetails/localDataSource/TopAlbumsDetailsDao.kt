package akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource

import akamal.de.lastfmappsfactory.data.albumsDetails.model.TopAlbumDetails
import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TopAlbumsDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAlbum(album: TopAlbumDetailsEntity): Completable

    @Query("SELECT * FROM albums")
    fun getAllAlbums(): Single<List<TopAlbumDetailsEntity>>

    @Query("SELECT * FROM albums WHERE albumId = :Id")
    fun getSingleAlbum(Id: String): Single<TopAlbumDetailsEntity>

    @Delete
    fun deleteSingleAlbum(album: TopAlbumDetailsEntity): Completable
}