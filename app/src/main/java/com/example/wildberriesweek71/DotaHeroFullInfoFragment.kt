package com.example.wildberriesweek71

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.wildberriesweek71.databinding.FragmentDotaHeroFullInfoBinding


class DotaHeroFullInfoFragment : Fragment() {
    lateinit var binding: FragmentDotaHeroFullInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDotaHeroFullInfoBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        val dotaHero = bundle?.getParcelable<DotaHeroesJSON>("dotaHero")
//        val name = bundle?.getString("name")
//        val image = bundle?.getString("image")
//        val attackRange = bundle?.getString("attackRange")
//        val attackRate = bundle?.getString("attackRate")
//        val baseHealth = bundle?.getString("BaseHealth")
//        val baseHealthRegen = bundle?.getString("BaseHealthRegen")
//        val baseMana = bundle?.getString("BaseMana")
//        val baseManaRegen = bundle?.getString("BaseManaRegen")
//        val moveSpeed = bundle?.getString("MoveSpeed")

        with(binding){
            tvNameSecondFragment.text = dotaHero?.name
            tvAttackRange.text = "Attack Range: ${dotaHero?.attack_range}"
            tvAttackRate.text = "Attack Rate: ${dotaHero?.attack_rate}"
            tvBaseHealth.text = "Base Health: ${dotaHero?.base_health}"
            tvBaseHealthRegen.text = "Base Health Regen: ${dotaHero?.base_health_regen}"
            tvBaseMana.text = "Base Mana: ${dotaHero?.base_mana}"
            tvBaseManaRegen.text = "Base Mana Regenbase: ${dotaHero?.base_mana_regen}"
            tvMoveSpeed.text = "Move Speed: ${dotaHero?.move_speed}"
            imageView.load("https://api.opendota.com${dotaHero?.img}")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = DotaHeroFullInfoFragment()
    }
}