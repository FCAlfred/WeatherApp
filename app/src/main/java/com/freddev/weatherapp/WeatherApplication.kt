package com.freddev.weatherapp

import android.app.Application
import com.freddev.weatherapp.data.db.WeatherDatabase
import com.freddev.weatherapp.data.network.ConnectivityInterceptor
import com.freddev.weatherapp.data.network.ConnectivityInterceptorImpl
import com.freddev.weatherapp.data.network.WeatherApiService
import com.freddev.weatherapp.data.network.WeatherNetworkDataSource
import com.freddev.weatherapp.data.network.WeatherNetworkDataSourceImpl
import com.freddev.weatherapp.data.repository.WeatherRepository
import com.freddev.weatherapp.data.repository.WeatherRepositoryImpl
import com.freddev.weatherapp.ui.weather.CurrentWeatherViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class WeatherApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@WeatherApplication))

        bind() from singleton { WeatherDatabase(instance()) }
        bind() from singleton { instance<WeatherDatabase>().currentWeatherDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { WeatherApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<WeatherRepository>() with singleton { WeatherRepositoryImpl(instance(), instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}