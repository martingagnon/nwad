package com.mirego.nwad.models.graphql

import kotlinx.serialization.Serializable

@Serializable
data class ValidationMessage(val code: String, val field: String?, val message: String?)
