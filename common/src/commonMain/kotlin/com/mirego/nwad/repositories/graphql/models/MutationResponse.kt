package com.mirego.nwad.repositories.graphql.models

import kotlinx.serialization.Serializable

@Serializable
data class MutationResponse<T>(
    val result: T?,
    val successful: Boolean,
)
