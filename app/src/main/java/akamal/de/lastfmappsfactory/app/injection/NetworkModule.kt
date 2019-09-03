package akamal.de.lastfmappsfactory.app.injection

import akamal.de.lastfmappsfactory.BuildConfig
import akamal.de.lastfmappsfactory.R
import akamal.de.lastfmappsfactory.app.constants.Constants
import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun providesAuthorizationInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val authRequest = chain.request().url.newBuilder().
            addQueryParameter("api_key", Constants.API_KEY).build()
            chain.proceed(originalRequest.newBuilder().url(authRequest).build())
        }
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(interceptor: Interceptor): OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(LoggingInterceptor()).build()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    class LoggingInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val t1 = System.nanoTime()
            Timber.e("Sending request ${request.url} ${chain.connection()} ${request.headers}")
            val response = chain.proceed(request)
            val t2 = System.nanoTime()
            Timber.e("Received response for ${response.request.url} ${(t2 - t1) / 1e6} ${response.headers}")
            return response
        }

    }
}