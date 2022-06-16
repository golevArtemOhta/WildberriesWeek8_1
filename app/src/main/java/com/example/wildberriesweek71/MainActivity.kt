package com.example.wildberriesweek71

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.wildberriesweek71.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            openFragment(DotaHeroesFragment.newInstance())
        }

        binding.imageButtonInfo.setOnClickListener {
            openFragment(InfoFragment.newInstance())
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, fragment)
            .addToBackStack("stack")
            .commit()
    }
}