package com.freddev.weatherapp.ui.weather

import android.location.Geocoder
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.freddev.weatherapp.data.WeatherApiService
import com.freddev.weatherapp.data.network.ConnectivityInterceptorImpl
import com.freddev.weatherapp.data.network.WeatherNetworkDataSourceImpl
import com.freddev.weatherapp.databinding.FragmentMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class MapsFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!
    private val args: MapsFragmentArgs by navArgs()
    private lateinit var googleMap: GoogleMap
    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        binding.map.apply {
            onCreate(savedInstanceState)
            onResume()
            getMapAsync(this@MapsFragment)
        }
        val connectivityInterceptor =
            ConnectivityInterceptorImpl(requireContext())

        val weatherApiService = WeatherApiService.invoke(connectivityInterceptor)

        val weatherNetworkDataSource = WeatherNetworkDataSourceImpl(weatherApiService)

        val viewModelFactory = CurrentWeatherViewModelFactory(weatherNetworkDataSource, args.city)
        viewModel = ViewModelProvider(this, viewModelFactory)[CurrentWeatherViewModel::class.java]

        setWeatherInfo()
        return binding.root
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val geocoder = Geocoder(requireContext())

        try {
            val addressList = geocoder.getFromLocationName(args.city, 1)
            if (addressList != null) {
                if (addressList.isNotEmpty()) {
                    val address = addressList[0]
                    val latLng = LatLng(address.latitude, address.longitude)
                    val zoomLevel = 12f
                    googleMap.addMarker(MarkerOptions().position(latLng).title(args.city))
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))
                } else {
                    Log.e("MapError", "No address for ${args.city}")
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("MapError", "GeoCodification Error: ${e.message}")
        }
    }


    private fun setWeatherInfo() {
        Log.i("setWeatherInfo", "Dentro de setWeatherInfo")
        viewModel.currentWeather.observe(viewLifecycleOwner) { weatherResponse ->
            Log.i("DataInfo: ", weatherResponse.toString())
            Toast.makeText(requireContext(), "DATA INFO: $weatherResponse", Toast.LENGTH_SHORT)
                .show()
            binding.apply {
                textViewLocalTime.text = "Local Time: \n${weatherResponse.location.localtime}"
                textViewCity.text = "Location: \n${weatherResponse.request.query}"
                textObservation.text =
                    "Observation Time: \n${weatherResponse.currentWeatherEntry.observationTime}"
                textFeelsLike.text =
                    "Feels Like: \n${weatherResponse.currentWeatherEntry.feelslike}° C"

                textTemperature.text =
                    "Temperature: \n${weatherResponse.currentWeatherEntry.temperature} °C"
                weatherResponse.currentWeatherEntry.weatherIcons.firstOrNull()?.let { iconUrl ->
                    Glide.with(requireContext())
                        .load(iconUrl)
                        .into(climateIcon)
                }
            }
        }
    }
}