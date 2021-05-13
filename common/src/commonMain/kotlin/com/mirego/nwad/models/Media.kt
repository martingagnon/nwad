package com.mirego.nwad.models

import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val xlarge: String,
    val large: String,
    val thumb: String,
    val teaser: String)