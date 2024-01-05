package com.freddev.weatherapp.ui.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.freddev.weatherapp.data.WeatherApiService
import com.freddev.weatherapp.data.network.ConnectivityInterceptorImpl
import com.freddev.weatherapp.data.network.WeatherNetworkDataSourceImpl
import com.freddev.weatherapp.databinding.FragmentCurrentWeatherBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CurrentWeatherFragment : Fragment() {

    private var _binding: FragmentCurrentWeatherBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[CurrentWeatherViewModel::class.java]

        _binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*        val textView: TextView = binding.textHome
                homeViewModel.text.observe(viewLifecycleOwner) {
                    textView.text = it
                }*/
        val apiService = WeatherApiService(ConnectivityInterceptorImpl(binding.root.context))
        val weatherNetworkDataSource = WeatherNetworkDataSourceImpl(apiService)

        weatherNetworkDataSource.downloadedCurrentWeather.observe(viewLifecycleOwner, Observer {
            binding.textHome.text = it.toString()
        })
        GlobalScope.launch(Dispatchers.Main) {
            weatherNetworkDataSource.fetchCurrentWeather("New York")
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}