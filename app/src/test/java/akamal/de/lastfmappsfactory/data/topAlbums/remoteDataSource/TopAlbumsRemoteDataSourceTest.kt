package akamal.de.lastfmappsfactory.data.topAlbums.remoteDataSource

import akamal.de.lastfmappsfactory.RxImmediateSchedulerRule
import akamal.de.lastfmappsfactory.data.topAlbums.model.AlbumImage
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbum
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbums
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbumsResponse
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.amshove.kluent.mock
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner.Silent::class)
open class TopAlbumsRemoteDataSourceTest {
    @Mock
    lateinit var service: TopAlbumsService

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var remoteDataSource: TopAlbumsRemoteDataSource

    val artist = "artistName"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        service = mock(TopAlbumsService::class)
        remoteDataSource = TopAlbumsRemoteDataSource(service)
    }

    @Test
    fun `when top albums are requested remotely, should success`() {
        Mockito.`when`(service.getTopAlbums(artist)).thenReturn(Single.just(getTopAlbumsResponse()))
        val genresSuccessObserver = TestObserver<TopAlbumsResponse>()

        val albums = remoteDataSource.getTopAlbums(artist)
        albums.subscribe(genresSuccessObserver)

        genresSuccessObserver.assertComplete()
        genresSuccessObserver.assertNoErrors()

        val moviesResult = genresSuccessObserver.values()[0]
        Assert.assertThat(moviesResult.topalbums.album.size, CoreMatchers.`is`(1))
        Assert.assertThat(moviesResult.topalbums.album[0].mbid, CoreMatchers.`is`("safd54sfd"))
        Assert.assertThat(moviesResult.topalbums.album[0].name, CoreMatchers.`is`("name"))
        Assert.assertThat(moviesResult.topalbums.album[0].image, CoreMatchers.`is`(getImages()))
        Assert.assertThat(moviesResult.topalbums.album[0].playcount, CoreMatchers.`is`(3654))

    }

    private fun getTopAlbumsResponse(): TopAlbumsResponse {
        val topAlbumList = ArrayList<TopAlbum>()
        val album = TopAlbum("safd54sfd", "name",getImages(), 3654)
        topAlbumList.add(album)
        return TopAlbumsResponse(TopAlbums(topAlbumList))
    }

    private fun getImages():List<AlbumImage> {
        val image = AlbumImage("url", "small")
        val imagesList = ArrayList<AlbumImage>()
        imagesList.add(image)
        imagesList.add(image)
        return imagesList
    }
}