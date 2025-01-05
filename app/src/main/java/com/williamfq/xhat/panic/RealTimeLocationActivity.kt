package com.williamfq.xhat.panic

import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.williamfq.xhat.databinding.ActivityRealTimeLocationBinding
import com.williamfq.domain.location.LocationTracker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RealTimeLocationActivity : AppCompatActivity() {

    // Inyectamos el LocationTracker utilizando Dagger Hilt
    @Inject
    lateinit var locationTracker: LocationTracker

    private lateinit var binding: ActivityRealTimeLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRealTimeLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Usamos el LocationTracker para obtener la ubicación en tiempo real
        lifecycleScope.launch {
            locationTracker.getCurrentLocation().collectLatest { location ->
                handleLocation(location)
            }
        }
    }

    private fun handleLocation(location: Location?) {
        if (location != null) {
            // Mostrar la ubicación en el TextView
            binding.tvLocation.text = "Lat: ${location.latitude}, Long: ${location.longitude}"
        } else {
            // En caso de error, mostrar un mensaje en el TextView
            binding.tvLocation.text = "No se pudo obtener la ubicación"
        }
    }
}
