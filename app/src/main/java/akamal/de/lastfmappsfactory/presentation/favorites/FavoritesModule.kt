package akamal.de.lastfmappsfactory.presentation.favorites

import akamal.de.lastfmappsfactory.app.injection.ViewModelKey
import akamal.de.lastfmappsfactory.data.favoriteAlbums.localDataSource.FavoritesLocalDataSource
import akamal.de.lastfmappsfactory.data.favoriteAlbums.repository.FavoritesRepository
import akamal.de.lastfmappsfactory.data.favoriteAlbums.repository.FavoritesRepositoryImpl
import akamal.de.lastfmappsfactory.domain.favoriteAlbums.FavoritesUseCase
import akamal.de.lastfmappsfactory.domain.favoriteAlbums.FavoritesUseCaseImpl
import akamal.de.lastfmappsfactory.presentation.topAlbums.AlbumListViewModule
import akamal.de.lastfmappsfactory.presentation.topAlbums.TopAlbumsFragment
import akamal.de.lastfmappsfactory.presentation.topAlbums.TopAlbumsViewModel
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module(includes = [FavoritesViewModelModule::class])
abstract class FavoritesModule {

    @ContributesAndroidInjector(modules = [AlbumListViewModule::class])
    abstract fun injectFavoritesFragment(): FavoritesFragment

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindsTopAlbumsViewModel(vm: FavoritesViewModel): ViewModel
}

@Module
class FavoritesViewModelModule {

    @Singleton
    @Provides
    fun providesFavoritesViewModel(localDataSource: FavoritesLocalDataSource): FavoritesRepository = FavoritesRepositoryImpl(localDataSource)

    @Singleton
    @Provides
    fun providesFavoritesUseCase(repository: FavoritesRepository): FavoritesUseCase = FavoritesUseCaseImpl(repository)
}