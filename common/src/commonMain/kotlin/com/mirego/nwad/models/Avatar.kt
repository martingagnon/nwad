package com.mirego.nwad.models

import kotlinx.serialization.Serializable

@Serializable
data class Avatar(
    val large: String,
    val thumb: String
)