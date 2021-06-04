package com.mirego.nwad.repositories.graphql.mutations

import com.mirego.nwad.models.graphql.DataResponse
import com.mirego.nwad.models.graphql.MutationResponse
import com.mirego.trikot.graphql.AbstractGraphqlQuery
import kotlinx.serialization.Serializable

@Serializable
data class GenerateDirectUploadPayloadResult(
    val originalUrl: String,
    val signedUrl: String,
    val url: String
)

@Serializable
data class GenerateDirectUploadPayloadMutationResponse(
    val generateDirectUploadPayload: MutationResponse<GenerateDirectUploadPayloadResult>
)

class GenerateDirectUploadPayloadMutation(private val filename: String) :
    AbstractGraphqlQuery<DataResponse<GenerateDirectUploadPayloadMutationResponse>>(
        DataResponse.serializer(GenerateDirectUploadPayloadMutationResponse.serializer())
    ) {
    override val variables: Map<String, Any> = mapOf(
        "filename" to filename
    )

    override val query: String
        get() = """
mutation(${'$'}filename: String!) {
  generateDirectUploadPayload(filename: ${'$'}filename) {
    successful
    messages {
      code
      field
      message
    }
  	result {
        originalUrl
        signedUrl
        url
    }  
  }
}""".trimIndent()
}
