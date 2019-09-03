package akamal.de.lastfmappsfactory.app.injection

import akamal.de.lastfmappsfactory.presentation.topAlbums.TopAlbumsModule
import dagger.Module

@Module(includes = [TopAlbumsModule::class])
class FeaturesModule