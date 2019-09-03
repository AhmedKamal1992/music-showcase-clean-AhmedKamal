package akamal.de.lastfmappsfactory.presentation.topAlbums

import akamal.de.lastfmappsfactory.app.injection.ViewModelKey
import akamal.de.lastfmappsfactory.data.topAlbums.remoteDataSource.TopAlbumsRemoteDataSource
import akamal.de.lastfmappsfactory.data.topAlbums.remoteDataSource.TopAlbumsService
import akamal.de.lastfmappsfactory.data.topAlbums.repository.TopAlbumsRepository
import akamal.de.lastfmappsfactory.data.topAlbums.repository.TopAlbumsRepositoryImpl
import akamal.de.lastfmappsfactory.data.topAlbumsDetails.localDataSource.TopAlbumsDetailsLocalDataSource
import akamal.de.lastfmappsfactory.domain.topAlbums.TopAlbumsUseCase
import akamal.de.lastfmappsfactory.domain.topAlbums.TopAlbumsUseCaseImpl
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [TopAlbumsViewModelModule::class, TopAlbumsApiModule::class])
abstract class TopAlbumsModule {

    @ContributesAndroidInjector(modules = [TopAlbumsViewModule::class])
    abstract fun injectTopAlbumsFragment(): TopAlbumsFragment

    @Binds
    @IntoMap
    @ViewModelKey(TopAlbumsViewModel::class)
    abstract fun bindsTopAlbumsViewModel(vm: TopAlbumsViewModel): ViewModel
}

@Module
class TopAlbumsViewModule {
    @Provides
    fun providesTopAlbumsAdapter(): TopAlbumsRvAdapter = TopAlbumsRvAdapter()
}

@Module
class TopAlbumsViewModelModule {

    @Singleton
    @Provides
    fun providesTopAlbumsRepository(localDataSource: TopAlbumsDetailsLocalDataSource, remoteDataSource: TopAlbumsRemoteDataSource): TopAlbumsRepository = TopAlbumsRepositoryImpl(remoteDataSource, localDataSource)

    @Singleton
    @Provides
    fun provesTopAlbumsUseCase(repository: TopAlbumsRepository): TopAlbumsUseCase = TopAlbumsUseCaseImpl(repository)

}

@Module
class TopAlbumsApiModule {

    @Singleton
    @Provides
    fun providesTopAlbumsService(retrofit: Retrofit): TopAlbumsService = retrofit.create(TopAlbumsService::class.java)
}