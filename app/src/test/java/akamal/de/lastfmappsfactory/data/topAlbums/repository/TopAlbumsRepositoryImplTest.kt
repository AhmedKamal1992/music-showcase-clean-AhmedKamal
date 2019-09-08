package akamal.de.lastfmappsfactory.data.topAlbums.repository

import akamal.de.lastfmappsfactory.data.common.DataResult
import akamal.de.lastfmappsfactory.data.common.DataSource
import akamal.de.lastfmappsfactory.data.topAlbums.model.AlbumImage
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbum
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbums
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbumsResponse
import akamal.de.lastfmappsfactory.data.topAlbums.remoteDataSource.TopAlbumsRemoteDataSource
import akamal.de.lastfmappsfactory.data.topAlbums.remoteDataSource.TopAlbumsService
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.exceptions.CompositeException
import io.reactivex.observers.TestObserver
import org.amshove.kluent.mock
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.*


class TopAlbumsRepositoryImplTest {
    @Mock
    lateinit var service: TopAlbumsService

    @Mock
    lateinit var remoteDataSource: TopAlbumsRemoteDataSource

    private lateinit var repository: TopAlbumsRepository

    private var response = getTopAlbumsResponse()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        service = mock(TopAlbumsService::class)

        remoteDataSource = mock(TopAlbumsRemoteDataSource::class)

        Mockito.`when`(service.getTopAlbums(ArgumentMatchers.anyString())).thenReturn(Single.just(response))

        Mockito.`when`(remoteDataSource.getTopAlbums(ArgumentMatchers.anyString())).thenReturn(Single.just(response))
        repository = TopAlbumsRepositoryImpl(remoteDataSource)
    }


    @Test
    fun `when searching with artist, should success`() {
        val artist = "artist"
        val searchSuccessObserver = TestObserver<DataResult<TopAlbumsResponse>>()

        val albumResult = repository.getTopAlbums(artist)
        albumResult.subscribe(searchSuccessObserver)

        assertNotNull(albumResult)
        val result = searchSuccessObserver.values()[0] as DataResult.Success
        assertEquals(result.result.topalbums.album[0].mbid, "safd54sfd")
        assertEquals(result.result.topalbums.album[0].name, "artist")
        assertEquals(result.result.topalbums.album[0].image, getImages())
        assertEquals(result.result.topalbums.album[0].playcount, 3654)
    }

    private fun getTopAlbumsResponse(): TopAlbumsResponse {
        val topAlbumList = ArrayList<TopAlbum>()
        val album = TopAlbum("safd54sfd", "artist",getImages(), 3654)
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