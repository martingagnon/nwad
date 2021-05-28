package com.mirego.nwad.repositories.graphql.mutations

import com.mirego.nwad.models.graphql.AccountsOrganization
import com.mirego.nwad.models.graphql.AccountsUser
import com.mirego.nwad.models.graphql.AuthenticateToken
import com.mirego.nwad.repositories.PlatformEnvironment
import com.mirego.nwad.repositories.graphql.models.DataResponse
import com.mirego.nwad.repositories.graphql.models.MutationResponse
import com.mirego.trikot.graphql.AbstractGraphqlQuery
import kotlinx.serialization.Serializable

@Serializable
data class AuthenticateProviderUserResult(
    val accessToken: AuthenticateToken,
    val refreshToken: AuthenticateToken,
    val organization: AccountsOrganization,
    val user: AccountsUser
)

class AuthenticateProviderUserMutation(private val token: String) :
    AbstractGraphqlQuery<DataResponse<MutationResponse<AuthenticateProviderUserResult>>>(
        DataResponse.serializer(MutationResponse.serializer(AuthenticateProviderUserResult.serializer()))
    ) {
    override val variables: Map<String, Any> = mapOf(
        "provider" to PlatformEnvironment.accountProvider,
        "token" to token
    )

    override val query: String
        get() = """
mutation(${'$'}token: String!, ${'$'}provider: AccountsProvider!) {
  authenticateProviderUser(token: ${'$'}token, provider: ${'$'}provider) {
    successful
    messages {
      code
      field
      message
    }
  	result {
      accessToken {
        token
      }      
      refreshToken {
        token
      }
      organization {
        name
      }
      user {
        id
        name
      }
    }  
  }
}""".trimIndent()
}
