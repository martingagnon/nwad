package com.mirego.nwad.models.graphql

import kotlinx.serialization.Serializable

@Serializable
data class DataResponse<T>(val data: T)
