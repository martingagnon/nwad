package com.mirego.nwad.models

import kotlinx.serialization.Serializable

@Serializable
data class Moment(
    val id: String,
    val media: Media,
    val created_at: String,
    val comments_count: Int,
    val description: String
)
