package com.example.wildberriesweek71

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.wildberriesweek71.databinding.DotaHeroItemBinding

class DotaHeroesAdapter : RecyclerView.Adapter<DotaHeroesAdapter.DotaHeroesHolder>() {
    val DotaHeroesList = ArrayList<DotaHeroesJSON>()

    class DotaHeroesHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = DotaHeroItemBinding.bind(item)


        @SuppressLint("SetTextI18n")
        fun bind(dotaHero: DotaHeroesJSON) = with(binding) {
            imAvatar.load("https://api.opendota.com${dotaHero.icon}")
            tvName.text = dotaHero.localized_name
            itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {

                    val activity = p0?.getContext() as AppCompatActivity
                    val dotaHeroFullInfoFragment: Fragment = DotaHeroFullInfoFragment()
                    val bundle = Bundle()
                    bundle.putParcelable("dotaHero", dotaHero)
//                    bundle.putString("name", dotaHero.localized_name)
//                    bundle.putString("image", dotaHero.img)
//                    bundle.putString("attackRange", dotaHero.attack_range.toString())
//                    bundle.putString("attackRate", dotaHero.attack_rate.toString())
//                    bundle.putString("BaseHealth", dotaHero.base_health.toString())
//                    bundle.putString("BaseHealthRegen", dotaHero.base_health_regen.toString())
//                    bundle.putString("BaseMana", dotaHero.base_mana.toString())
//                    bundle.putString("BaseManaRegen", dotaHero.base_mana_regen.toString())
//                    bundle.putString("MoveSpeed", dotaHero.move_speed.toString())

                    dotaHeroFullInfoFragment.arguments = bundle
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment, dotaHeroFullInfoFragment).addToBackStack(null)
                        .commit()

                }
            })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DotaHeroesHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dota_hero_item, parent, false)
        return DotaHeroesHolder(view)
    }

    override fun onBindViewHolder(holder: DotaHeroesHolder, position: Int) {
        holder.bind(DotaHeroesList[position])
    }

    override fun getItemCount(): Int {
        return DotaHeroesList.size
    }

    fun getDotaHeroesData(dotaHeroes: List<DotaHeroesJSON>) {
        DotaHeroesList.addAll(dotaHeroes)
    }
}