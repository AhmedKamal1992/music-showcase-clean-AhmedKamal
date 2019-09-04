package akamal.de.lastfmappsfactory.presentation.albumDetails

import akamal.de.lastfmappsfactory.app.injection.ViewModelKey
import akamal.de.lastfmappsfactory.data.albumsDetails.localDataSource.AlbumsDetailsLocalDataSource
import akamal.de.lastfmappsfactory.data.albumsDetails.remoteDataSource.AlbumDetailsRemoteDataSource
import akamal.de.lastfmappsfactory.data.albumsDetails.remoteDataSource.AlbumDetailsService
import akamal.de.lastfmappsfactory.data.albumsDetails.repository.AlbumDetailsRepository
import akamal.de.lastfmappsfactory.data.albumsDetails.repository.AlbumDetailsRepositoryImpl
import akamal.de.lastfmappsfactory.domain.albumDetails.AlbumDetailsUseCase
import akamal.de.lastfmappsfactory.domain.albumDetails.AlbumDetailsUseCaseImpl
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [AlbumDetailsApiModule::class, AlbumDetailsViewModelModule::class])
abstract class AlbumDetailsModule {

    @ContributesAndroidInjector(modules = [AlbumDetailsViewModule::class])
    abstract fun injectAlbumDetailsFragment(): AlbumDetailsFragment

    @Binds
    @IntoMap
    @ViewModelKey(AlbumDetailsViewModel::class)
    abstract fun bindsAlbumDetailsViewModel(viewModel: AlbumDetailsViewModel): ViewModel
}

@Module
class AlbumDetailsViewModule {

    @Provides
    fun providesTracksAdapter(): AlbumDetailsTracksRvAdapter = AlbumDetailsTracksRvAdapter()
}

@Module
class AlbumDetailsViewModelModule {

    @Singleton
    @Provides
    fun providesAlbumDetailsRepository(localDataSource: AlbumsDetailsLocalDataSource, remoteDataSource: AlbumDetailsRemoteDataSource): AlbumDetailsRepository = AlbumDetailsRepositoryImpl(remoteDataSource, localDataSource)

    @Singleton
    @Provides
    fun providesAlbumDetailsUseCase(repository: AlbumDetailsRepository): AlbumDetailsUseCase = AlbumDetailsUseCaseImpl(repository)
}

@Module
class AlbumDetailsApiModule {

    @Singleton
    @Provides
    fun providesAlbumDetailsService(retrofit: Retrofit): AlbumDetailsService = retrofit.create(AlbumDetailsService::class.java)
}