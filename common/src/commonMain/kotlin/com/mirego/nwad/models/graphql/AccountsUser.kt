package com.mirego.nwad.models.graphql

import kotlinx.serialization.Serializable

@Serializable
data class AccountsUser(val id: String, val name: String)

@Serializable
data class AccountsOrganization(val name: String)

@Serializable
data class AuthenticateToken(val token: String)
