package com.mirego.nwad.models.graphql

import kotlinx.serialization.Serializable

@Serializable
data class MutationResponse<T>(
    val result: T?,
    val successful: Boolean,
    val messages: List<ValidationMessage>?
)
