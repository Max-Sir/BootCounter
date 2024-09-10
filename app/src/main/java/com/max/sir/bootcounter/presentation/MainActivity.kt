package com.max.sir.bootcounter.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.max.sir.bootcounter.R
import com.max.sir.bootcounter.data.entity.AppConfig
import com.max.sir.bootcounter.data.entity.BootEvent
import com.max.sir.bootcounter.databinding.ActivityMainBinding
import com.max.sir.bootcounter.presentation.viewmodel.BootCounterViewModel

class MainActivity : AppCompatActivity() {

    // ViewModel that handles the business logic
    private val viewModel: BootCounterViewModel by viewModels()

    // ViewBinding instance for activity_main.xml
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize UI elements and observers
        setupUI()
        observeViewModel()
    }

    /**
     * Set up the UI, adding listeners and configuring inputs.
     */
    private fun setupUI() {
        // Set the update button click listener
        binding.updateButton.setOnClickListener {
            handleConfigUpdate()
        }
    }

    /**
     * Observe the ViewModel for any changes in the boot event history or config.
     */
    private fun observeViewModel() {
        // Observe boot event history
        viewModel.bootEvents.observe(this, Observer { events ->
            displayBootEvents(events)
        })

        // Observe configuration changes (optional if you want dynamic UI updates)
        viewModel.config.observe(this, Observer { config ->
            binding.dismissalsAllowed.setText(config.dismissalsAllowed.toString())
            binding.dismissalInterval.setText(config.dismissalInterval.toString())
        })
    }

    /**
     * Handle the update configuration event triggered by the user clicking the button.
     */
    private fun handleConfigUpdate() {
        val dismissalsAllowed =
            binding.dismissalsAllowed.text.toString().toIntOrNull() ?: DEFAULT_DISMISSALS_ALLOWED
        val interval = binding.dismissalInterval.text.toString().toIntOrNull() ?: DEFAULT_INTERVAL

        val newConfig =
            AppConfig(id = 1, dismissalsAllowed = dismissalsAllowed, dismissalInterval = interval)

        // Send the updated configuration to the ViewModel
        viewModel.updateConfig(newConfig)
    }

    /**
     * Display the boot events in the scrollable TextView. Format the list to show
     * boot events and the number of occurrences per day.
     */
    private fun displayBootEvents(events: List<BootEvent>) {
        if (events.isEmpty()) {
            binding.bootEvents.text = getString(R.string.no_boots_detected)
        } else {
            val formattedEvents = events
                .groupBy { it.bootTime }
                .map { (date, eventList) -> "${date.toString()} - ${eventList.size}" }
                .joinToString(separator = "\n")

            binding.bootEvents.text = formattedEvents
        }
    }

    companion object {
        private const val DEFAULT_DISMISSALS_ALLOWED = 5
        private const val DEFAULT_INTERVAL = 20
    }
}