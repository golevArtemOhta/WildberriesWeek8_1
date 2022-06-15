package com.example.wildberriesweek71

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class DotaHeroesJSON(
    val attack_range: Int,
    val attack_rate: Double,
    val attack_type: String,
    val base_armor: Double,
    val base_health: Int,
    val base_health_regen: Double,
    val base_mana: Int,
    val base_mana_regen: Double,
    val hero_id: Int,
    val icon: String,
    val id: Int,
    val img: String,
    val localized_name: String,
    val move_speed: Int,
    val name: String,
    val roles: List<String>,
)