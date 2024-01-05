package com.freddev.weatherapp.ui.weather

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.freddev.weatherapp.R
import com.freddev.weatherapp.databinding.FragmentCurrentWeatherBinding

class CurrentWeatherFragment : Fragment() {

    private var _binding: FragmentCurrentWeatherBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val mainBackgroundResource =
            "https://img.europapress.es/fotoweb/fotonoticia_20150204060231-709969_640.webp"
        Glide.with(requireContext())
            .load(
                mainBackgroundResource
            )
            .into(binding.weatherBackground)
        binding.buttonMap.setOnClickListener {
            val city: String = binding.editText.text.trim().toString()
            if (city.isEmpty()) {
                binding.editText.apply {
                    setHintTextColor(Color.RED)
                    setHint(R.string.TYPE_CITY)
                }
            } else {
                val action =
                    CurrentWeatherFragmentDirections.actionCurrentWeatherToMapsFragment(city)
                findNavController().navigate(action)
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}