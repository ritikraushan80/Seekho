package com.seekho.views.activity

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.seekho.R
import com.seekho.databinding.ActivityMainBinding
import com.seekho.utils.SharedPref
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    private var backPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val settings: SharedPreferences = applicationContext.getSharedPreferences(
            applicationContext.packageName, MODE_PRIVATE
        )
        settings.edit().apply()
        SharedPref.init(this)

        binding.root.post {
            navController = findNavController(R.id.nav_host_fragment_activity_main)
        }

    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.homeFragment) {
            if (backPressedOnce) {
                finish()
            } else {
                backPressedOnce = true
                Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
                Handler(Looper.getMainLooper()).postDelayed({ backPressedOnce = false }, 2000)
            }
        } else {
            if (!navController.navigateUp()) {
                super.onBackPressed()
            }
        }

    }
}