package akamal.de.lastfmappsfactory.app.injection

import akamal.de.lastfmappsfactory.presentation.albumDetails.AlbumDetailsModule
import akamal.de.lastfmappsfactory.presentation.topAlbums.TopAlbumsModule
import dagger.Module

@Module(includes = [TopAlbumsModule::class, AlbumDetailsModule::class])
class FeaturesModule