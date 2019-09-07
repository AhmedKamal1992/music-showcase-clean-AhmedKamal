package akamal.de.lastfmappsfactory.presentation.topAlbums

import akamal.de.lastfmappsfactory.R
import akamal.de.lastfmappsfactory.app.application.AndroidApplication
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
        topAlbumsViewState.value = BaseViewState.Error(getApplication<AndroidApplication>().getString(R.string.empty_result))
    }

    fun getAllAlbums(artistName: String) {
        topAlbumsViewState.value = BaseViewState.Loading()
        if(artistName.isNotEmpty()) {
            compositeDisposable.add(useCase.getAllAlbums(artistName).subscribe ({dataResult ->
                when(dataResult) {
                    is DataResult.Success -> { topAlbumsViewState.value = BaseViewState.Success(dataResult.result.topalbums.album) }
                    is DataResult.Error -> { dataResult.throwable.message?.let { topAlbumsViewState.value = BaseViewState.Error(it) } }
                }},{ throwable -> throwable.message?.let { topAlbumsViewState.value = BaseViewState.Error(it) } }))
        } else { topAlbumsViewState.value = BaseViewState.Error(getApplication<AndroidApplication>().getString(R.string.empty_artist)) }
    }

}