package com.khin.fashion.shop.presentation.shop

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.*
import com.khin.fashion.shop.presentation.databinding.ShopFragmentBinding
import com.khin.fashion.shop.presentation.utls.PermissionUtils
import org.koin.androidx.viewmodel.ext.android.viewModel


class ShopFragment : Fragment() {

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 44
    }

    private var _binding: ShopFragmentBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ShopViewModel by viewModel()

    private val adapter: ShopAdapter by lazy { ShopAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.load()
        _binding = ShopFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.viewState.observe(viewLifecycleOwner, { shopViewState ->
            shopViewState.showPickUpLocations()
            shopViewState.showOverlayViewShop()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkPermission() {
        when {
            PermissionUtils.isAccessFineLocationGranted(requireContext()) -> {
                when {
                    PermissionUtils.isLocationEnabled(requireContext()) -> {
                        setUpLocationListener()
                    }
                    else -> {
                        PermissionUtils.showGPSNotEnabledDialog(requireContext())
                    }
                }
            }
            else -> {
                PermissionUtils.requestAccessFineLocationPermission(
                    activity as AppCompatActivity,
                    LOCATION_PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun setUpLocationListener() {
        val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        val locationRequest = LocationRequest()
            .setInterval(1000)
            .setFastestInterval(1000)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    for (location in locationResult.locations) {
                        viewModel.currentLocation.value?.let {
                            if (it.distanceTo(location) > 10f) {
                                viewModel.load()
                                viewModel.currentLocation.value = location
                            }
                        } ?: kotlin.run {
                            viewModel.currentLocation.value = location
                        }
                    }
                }
            },
            Looper.myLooper()!!
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    when {
                        PermissionUtils.isLocationEnabled(requireContext()) -> {
                            setUpLocationListener()
                        }
                        else -> {
                            PermissionUtils.showGPSNotEnabledDialog(requireContext())
                        }
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Permission not granted",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun initViews() {
        binding.recyclerViewShop.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewShop.adapter = adapter

        binding.floatingButtonShopGetLocation.setOnClickListener {
            checkPermission()
        }
    }

    private fun ShopViewState.showPickUpLocations() {
        adapter.localPickup = localPickup

    }

    private fun ShopViewState.showOverlayViewShop() {
        binding.overlayViewShop.setViewState(overlayViewState)
    }

}