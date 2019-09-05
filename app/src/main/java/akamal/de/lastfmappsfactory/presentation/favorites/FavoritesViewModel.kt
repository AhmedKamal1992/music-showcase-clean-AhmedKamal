package akamal.de.lastfmappsfactory.presentation.favorites

import akamal.de.lastfmappsfactory.R
import akamal.de.lastfmappsfactory.app.application.AndroidApplication
import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.TopAlbumDetailsEntity
import akamal.de.lastfmappsfactory.data.common.DataResult
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbum
import akamal.de.lastfmappsfactory.domain.favoriteAlbums.FavoritesUseCase
import akamal.de.lastfmappsfactory.platform.bases.BaseViewModel
import akamal.de.lastfmappsfactory.platform.bases.BaseViewState
import android.app.Application
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(application: Application, private val usecase: FavoritesUseCase): BaseViewModel(application) {

    val favoritesViewState: MutableLiveData<BaseViewState<List<TopAlbum>>> = MutableLiveData()

    fun getAllAlbums() {
        favoritesViewState.value = BaseViewState.Loading()
        compositeDisposable.add(usecase.getFavorites().subscribe {
            dataResult ->
                when(dataResult) {
                    is DataResult.Success -> { if(dataResult.result.isNotEmpty()) favoritesViewState.value = BaseViewState.Success(dataResult.result)
                    else favoritesViewState.value = BaseViewState.Error(getApplication<AndroidApplication>().resources.getString(R.string.no_favorites)) }

                    is DataResult.Error -> { dataResult.throwable.message?.let { favoritesViewState.value = BaseViewState.Error(it) } }
                }
        })
    }
}