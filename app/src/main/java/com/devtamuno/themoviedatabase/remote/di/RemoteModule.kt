package com.devtamuno.themoviedatabase.remote.di

import com.devtamuno.themoviedatabase.remote.env.Env
import com.devtamuno.themoviedatabase.remote.repository.MoviesRemoteRepository
import com.devtamuno.themoviedatabase.remote.repository.MoviesRemoteRepositoryImpl
import com.devtamuno.themoviedatabase.remote.service.MovieService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit


@Module
@ExperimentalSerializationApi
@InstallIn(SingletonComponent::class)
internal object RemoteModule {

    private val config = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    @[Provides Singleton]
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(config.asConverterFactory("application/json".toMediaType()))
            .client(client).build()
    }

    @[Provides Singleton]
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)

        okHttpBuilder.addInterceptor { chain: Interceptor.Chain ->
            val request = chain.request()
            val url = request.url.newBuilder()
                .addQueryParameter(name = Env.API_KEY_NAME, value = Env.API_KEY).build()
            chain.proceed(request.newBuilder().url(url).build())
        }

        return okHttpBuilder.build()
    }

    @[Provides Singleton]
    fun providesMovieService(client: Retrofit): MovieService {
        return client.create(MovieService::class.java)
    }

    @[Provides Singleton]
    fun providesFootballRemoteRepository(service: MovieService): MoviesRemoteRepository {
        return MoviesRemoteRepositoryImpl(service)
    }

}