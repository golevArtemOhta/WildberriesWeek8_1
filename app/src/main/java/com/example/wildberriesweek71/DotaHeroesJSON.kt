package com.example.wildberriesweek71

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class DotaHeroesJSON(
    val attack_range: Int,
    val attack_rate: Double,
    val attack_type: String?,
    val base_armor: Double,
    val base_health: Int,
    val base_health_regen: Double,
    val base_mana: Int,
    val base_mana_regen: Double,
    val hero_id: Int,
    val icon: String?,
    val id: Int,
    val img: String?,
    val localized_name: String?,
    val move_speed: Int,
    val name: String?,
    val roles: List<String>?,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.createStringArrayList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(attack_range)
        parcel.writeDouble(attack_rate)
        parcel.writeString(attack_type)
        parcel.writeDouble(base_armor)
        parcel.writeInt(base_health)
        parcel.writeDouble(base_health_regen)
        parcel.writeInt(base_mana)
        parcel.writeDouble(base_mana_regen)
        parcel.writeInt(hero_id)
        parcel.writeString(icon)
        parcel.writeInt(id)
        parcel.writeString(img)
        parcel.writeString(localized_name)
        parcel.writeInt(move_speed)
        parcel.writeString(name)
        parcel.writeStringList(roles)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DotaHeroesJSON> {
        override fun createFromParcel(parcel: Parcel): DotaHeroesJSON {
            return DotaHeroesJSON(parcel)
        }

        override fun newArray(size: Int): Array<DotaHeroesJSON?> {
            return arrayOfNulls(size)
        }
    }
}