package com.mirego.nwad.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val full_name: String,
    val avatar: Avatar,
    val title: String?
)