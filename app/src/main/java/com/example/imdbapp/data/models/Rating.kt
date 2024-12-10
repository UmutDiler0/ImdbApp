package com.example.imdbapp.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    @SerializedName("Source") val source: String,
    @SerializedName("Value") val value: String
)