package akamal.de.lastfmappsfactory.presentation.albumDetails

import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.TopAlbumDetailsEntity
import akamal.de.lastfmappsfactory.data.albumsDetails.model.TopAlbumDetails
import akamal.de.lastfmappsfactory.data.albumsDetails.model.toAlbumEntity
import akamal.de.lastfmappsfactory.data.common.DataResult
import akamal.de.lastfmappsfactory.domain.albumDetails.AlbumDetailsUseCase
import akamal.de.lastfmappsfactory.platform.bases.BaseViewModel
import android.app.Application
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class AlbumDetailsViewModel @Inject constructor(application: Application, private val useCase: AlbumDetailsUseCase): BaseViewModel(application) {

    val albumDetailsMutable: MutableLiveData<TopAlbumDetails> = MutableLiveData()
    val isSavedInDbMutable: MutableLiveData<Boolean> = MutableLiveData()

    fun getAlbumDetails(albumId: String) {
        compositeDisposable.add(useCase.getAlbumDetails(albumId).subscribe {
            dataResult ->
                when(dataResult) {
                    is DataResult.Success -> { albumDetailsMutable.value = dataResult.result.album }
                    is DataResult.Error -> {  }
                }
        })
    }

    fun saveOrRemoveAlbum(album: TopAlbumDetails) {
        album.let { it.isFavorite?.let { if(it) { removeSingleAlbum(album.toAlbumEntity()) } else saveSingleAlbum(album.toAlbumEntity()) } }
    }

    private fun saveSingleAlbum(album: TopAlbumDetailsEntity) {
        compositeDisposable.add(useCase.saveSingleAlbum(album).subscribe ({ isSavedInDbMutable.value = true },{ it?.message?.let { toastMutable.value = it } }))
    }

    private fun removeSingleAlbum(album: TopAlbumDetailsEntity) {
        compositeDisposable.add(useCase.removeSingleAlbum(album).subscribe ({ isSavedInDbMutable.value = false },{ it?.message?.let { toastMutable.value = it } }))
    }
}