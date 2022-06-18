package com.example.wildberriesweek71

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.wildberriesweek71.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            openFragment(DotaHeroesFragment.newInstance(), null)
        }

        binding.imageButtonInfo.setOnClickListener {
            openFragment(InfoFragment.newInstance(), "stack")
        }
    }

    private fun openFragment(fragment: Fragment, stackName: String?) {
        if (!fragment.isAdded)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment, fragment)
                .addToBackStack(stackName)
                .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack("stack", FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }


}