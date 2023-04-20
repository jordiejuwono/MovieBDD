package com.jordju.moviebdd.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jordju.moviebdd.data.remote.datasource.MovieRemoteDataSource
import com.jordju.moviebdd.data.remote.datasource.MovieRemoteDataSourceImpl
import com.jordju.moviebdd.data.remote.mapper.MovieMapper
import com.jordju.moviebdd.data.remote.repository.MovieRepositoryImpl
import com.jordju.moviebdd.data.remote.service.ApiService
import com.jordju.moviebdd.domain.repository.MovieRepository
import com.jordju.moviebdd.domain.usecase.GetNowPlayingMoviesUseCase
import com.jordju.moviebdd.domain.usecase.GetTopRatedMoviesUseCase
import com.jordju.moviebdd.domain.usecase.GetUpcomingMoviesUseCase
import com.jordju.moviebdd.ui.screen.MainViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object AppModules {

    fun getModules(): List<Module> {
        return listOf(
            networkModule,
            mapperModule,
            dataSourceModule,
            repositoryModule,
            viewModelModule,
            useCaseModule
        )
    }

    private val networkModule = module {
        single {
            OkHttpClient.Builder()
                .addInterceptor(ChuckerInterceptor.Builder(androidContext()).build())
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
        }
        single {
            Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
                .create(ApiService::class.java)
        }
    }

    private val mapperModule = module {
        single { MovieMapper }
    }

    private val dataSourceModule = module {
        single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(get()) }
    }

    private val repositoryModule = module {
        single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    }

    private val viewModelModule = module {
        viewModel { MainViewModel(get()) }
    }

    private val useCaseModule = module {
        single { GetTopRatedMoviesUseCase(get()) }
        single { GetNowPlayingMoviesUseCase(get()) }
        single { GetUpcomingMoviesUseCase(get()) }
    }

}