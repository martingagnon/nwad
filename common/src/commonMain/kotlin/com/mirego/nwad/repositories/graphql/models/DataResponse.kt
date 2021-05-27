package com.mirego.nwad.repositories.graphql.models

import kotlinx.serialization.Serializable

@Serializable
data class DataResponse<T>(val data: T)
