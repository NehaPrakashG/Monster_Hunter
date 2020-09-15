package com.example.monsterhunter.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class ArmorDataResponse(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("type")
    @Expose
    var type: String? = null,

    @SerializedName("rank")
    @Expose
    var rank: String? = null,

    @SerializedName("rarity")
    @Expose
    var rarity: Int? = null,

    @SerializedName("defense")
    @Expose
    var defense: Defense? =null,

    @SerializedName("slots")
    @Expose
    var slots: List<Slots> = ArrayList(),

    @SerializedName("skills")
    @Expose
    var skills: List<Skill> = ArrayList(),

    @SerializedName("assets")
    @Expose
    var assets: Asset? = null,

    @SerializedName("crafting")
    @Expose
    var crafting: Crafting? = null

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int

    )



    companion object CREATOR : Parcelable.Creator<ArmorDataResponse> {
        override fun createFromParcel(parcel: Parcel): ArmorDataResponse {
            return ArmorDataResponse(parcel)
        }

        override fun newArray(size: Int): Array<ArmorDataResponse?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {

    }
}


class Asset {

    @SerializedName("imageMale")
    @Expose
    var imageMale: String? = null
    @SerializedName("imageFemale")
    @Expose
    var imageFemale: String? = null

}
class Skill {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("level")
    @Expose
    var level: Int? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("skill")
    @Expose
    var skill: Int? = null
    @SerializedName("skillName")
    @Expose
    var skillName: String? = null
}
class Slots {
    @SerializedName("rank")
    @Expose
    var rank: String? = null


}
class Crafting {

    @SerializedName("materials")
    @Expose
    var materials: List<Material> = ArrayList()
}
class Material {

    @SerializedName("quantity")
    @Expose
    var quantity: Int? = null
    @SerializedName("item")
    @Expose
    var item: Item? = null
}
class Item {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("rarity")
    @Expose
    var rarity: Int? = null
    @SerializedName("carryLimit")
    @Expose
    var carryLimit: Int? = null
    @SerializedName("sellPrice")
    @Expose
    var sellPrice: Int? = null
    @SerializedName("buyPrice")
    @Expose
    var buyPrice: Int? = null
}

data class Defense (

    @SerializedName("base") val base : Int,
    @SerializedName("max") val max : Int,
    @SerializedName("augmented") val augmented : Int
)