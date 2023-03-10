package com.example.bottomnavigationwork

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bottomnavigationwork.data.local.Pref
import com.example.bottomnavigationwork.databinding.ActivityMainBinding
import com.example.bottomnavigationwork.ui.home.HomeFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    private lateinit var pref: Pref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = Pref(this)
        auth = FirebaseAuth.getInstance()

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        if (auth.currentUser?.uid == null) {
            navController.navigate(HomeFragmentDirections.actionToAuth())
        } else if (!pref.isUser())
            navController.navigate(HomeFragmentDirections.actionNavigationHomeToOnBoardingFragment())

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.profileFragment,
                R.id.taskFragment,

                )
        )
        val bottomNavFragments = arrayListOf(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications,)

        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            navView.isVisible = bottomNavFragments.contains(destination.id)
            if (destination.id == R.id.onBoardingFragment){
                supportActionBar?.hide()
            }else supportActionBar?.show()
        }
        navView.setupWithNavController(navController)

        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            Log.d("aa", it.toString())
        }

    }
}