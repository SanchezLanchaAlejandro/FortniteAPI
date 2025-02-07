package com.example.fortniteapi

import com.google.gson.annotations.SerializedName
import okhttp3.Response

data class CosmeticsResponse(
    @SerializedName("data") val data: List<CosmeticItem>
)

data class CosmeticItem(
    @SerializedName("id") val id: String,
    @SerializedName("name") val nombre: String,
    @SerializedName("description") val descripcion: String,
    @SerializedName("type") val tipo: CosmeticType,
    @SerializedName("rarity") val rareza: CosmeticRarity,
    @SerializedName("set") val set: CosmeticSet,
    @SerializedName("introduction") val introduccion: CosmeticIntroduction,
    @SerializedName("images") val imagen: CosmeticImage,
)

data class CosmeticImage(
    @SerializedName("icon") val url: String
)

data class CosmeticType(
    @SerializedName("value") val tipo: String
)

data class CosmeticRarity(
    @SerializedName("displayValue") val rareza: String
)

data class CosmeticSet(
    @SerializedName("text") val set: String
)

data class CosmeticIntroduction(
    @SerializedName("text") val introduccion: String
)