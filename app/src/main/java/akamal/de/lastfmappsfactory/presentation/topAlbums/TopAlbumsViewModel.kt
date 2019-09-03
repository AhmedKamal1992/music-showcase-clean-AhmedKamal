package akamal.de.lastfmappsfactory.presentation.topAlbums

import akamal.de.lastfmappsfactory.data.common.DataResult
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbum
import akamal.de.lastfmappsfactory.domain.topAlbums.TopAlbumsUseCase
import akamal.de.lastfmappsfactory.platform.bases.BaseViewModel
import akamal.de.lastfmappsfactory.platform.bases.BaseViewState
import android.app.Application
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class TopAlbumsViewModel @Inject constructor(application: Application, private val useCase: TopAlbumsUseCase): BaseViewModel(application) {

    val topAlbumsViewState: MutableLiveData<BaseViewState<List<TopAlbum>>> = MutableLiveData()

    init {
        getAllAlbums()
    }

    private fun getAllAlbums() {
        topAlbumsViewState.value = BaseViewState.Loading()
        compositeDisposable.add(useCase.getAllAlbums().subscribe ({dataResult ->
            when(dataResult) {
                is DataResult.Success -> { topAlbumsViewState.value = BaseViewState.Success(dataResult.result.topalbums.album) }
                is DataResult.Error -> { dataResult.throwable.message?.let { topAlbumsViewState.value = BaseViewState.Error(it) } }
            }},{ throwable -> throwable.message?.let { topAlbumsViewState.value = BaseViewState.Error(it) } }))
    }

}