package com.mirego.nwad.repositories.graphql.mutations

import com.mirego.nwad.models.SignedUrl
import com.mirego.nwad.models.graphql.DataResponse
import com.mirego.nwad.models.graphql.MutationResponse
import com.mirego.trikot.graphql.AbstractGraphqlQuery
import kotlinx.serialization.Serializable

@Serializable
data class GenerateSignedUrlResponse(val generateSignedUrl: MutationResponse<SignedUrl>)

class GenerateSignedUrlMutation(private val filename: String) :
    AbstractGraphqlQuery<DataResponse<GenerateSignedUrlResponse>>(
        DataResponse.serializer(GenerateSignedUrlResponse.serializer())
    ) {
    override val variables: Map<String, Any>? = mapOf(
        "filename" to filename
    )

    override val query: String
        get() = """
mutation(${'$'}filename: String!) {
  generateSignedUrl(filename: ${'$'}filename) {
    successful
    messages {
      code
      field
      message
    }
  	result {
        signedUrl
    }  
  }
}""".trimIndent()
}
