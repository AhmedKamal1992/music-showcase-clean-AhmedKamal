package akamal.de.lastfmappsfactory.data.favoriteAlbums.localDataSource

import akamal.de.lastfmappsfactory.app.database.ApplicationDatabase
import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.TopAlbumDetailsEntity
import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.TopAlbumsDetailsDao
import akamal.de.lastfmappsfactory.data.albumsDetails.model.AlbumTracks
import akamal.de.lastfmappsfactory.data.albumsDetails.model.Artist
import akamal.de.lastfmappsfactory.data.albumsDetails.model.Tracks
import akamal.de.lastfmappsfactory.data.topAlbums.model.AlbumImage
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.reactivex.observers.TestObserver
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AppLocalDataSourceTest {

    private lateinit var appDatabase: ApplicationDatabase
    private lateinit var dao: TopAlbumsDetailsDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(context, ApplicationDatabase::class.java).build()
        dao = appDatabase.topAlbumsDetailsDao()
        dao.saveAlbum(getResponse("1", "artist1"))
        dao.saveAlbum(getResponse("1", "artist1"))
        dao.saveAlbum(getResponse("1", "artist1"))
        dao.saveAlbum(getResponse("1", "artist1"))
        dao.saveAlbum(getResponse("1", "artist1"))

    }

    @Test
    fun insertingDataInDatabaseShouldSuccess() {
        dao.saveAlbum(getResponse("123", "artist1"))
        val result = dao.getAllAlbums()

        val testObserver = TestObserver<List<TopAlbumDetailsEntity>>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    @Throws(Exception::class)
    fun shouldSuccessWhenRetrievingDataFromDatabase() {
        dao.saveAlbum(getResponse("1", "artist1"))
        val result = dao.getSingleAlbum("1")

        val listResult = result.blockingGet()
        assertThat(listResult.mbid, CoreMatchers.`is`("id"))
        assertThat(listResult.artist, CoreMatchers.`is`("Artist"))
        assertThat(listResult.image, CoreMatchers.`is`(getImages()))
        assertThat(listResult.name, CoreMatchers.`is`("OL"))
        assertThat(listResult.playcount, CoreMatchers.`is`("485648564"))
        assertThat(listResult.tracks, CoreMatchers.`is`(Tracks(getTracks())))
    }

    private fun getResponse(mbid: String, givenArtist: String): TopAlbumDetailsEntity {
        return TopAlbumDetailsEntity(mbid, givenArtist,getImages(), "OL", "485648564",
            Tracks(getTracks()))
    }

    private fun getTracks():List<AlbumTracks> {
        val track = AlbumTracks(Artist("id", "name", "url"), "100", "name", "url")
        val tracksList = ArrayList<AlbumTracks>()
        tracksList.add(track)
        tracksList.add(track)
        return tracksList
    }

    private fun getImages():List<AlbumImage> {
        val image = AlbumImage("url", "small")
        val imagesList = ArrayList<AlbumImage>()
        imagesList.add(image)
        imagesList.add(image)
        return imagesList
    }

    @After
    fun closeDatabase() {
        appDatabase.close()
    }
}