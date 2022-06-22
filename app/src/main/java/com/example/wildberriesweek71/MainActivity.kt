package com.example.wildberriesweek71

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.wildberriesweek71.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    lateinit var itemInfo: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            openFragment(DotaHeroesFragment.newInstance(), null)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.info) {
            openFragment(InfoFragment.newInstance(),null)
            itemInfo.isVisible = false
        }
        return true
    }

    private fun openFragment(fragment: Fragment, stackName: String?) {
        if (!fragment.isAdded)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment, fragment)
                .addToBackStack(stackName)
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        itemInfo = menu!!.findItem(R.id.info)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        itemInfo.isVisible = true
        supportFragmentManager.popBackStack("stack", FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }


}