package akamal.de.lastfmappsfactory.app.injection

import akamal.de.lastfmappsfactory.app.application.AndroidApplication
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    AppModule::class, NetworkModule::class,
    ViewModelFactoryBindingModule::class,
    DatabaseModule::class, FeaturesModule::class])

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application):Builder
        fun appModule(module: AppModule):Builder
        fun networkModule(module: NetworkModule):Builder
        fun inject(): AppComponent
    }
    fun inject(app: AndroidApplication)
}