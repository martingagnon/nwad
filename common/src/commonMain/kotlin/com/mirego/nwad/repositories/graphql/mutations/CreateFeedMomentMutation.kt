package com.mirego.nwad.repositories.graphql.mutations

import com.mirego.nwad.repositories.graphql.models.DataResponse
import com.mirego.nwad.repositories.graphql.models.IdResponse
import com.mirego.nwad.repositories.graphql.models.MutationResponse
import com.mirego.trikot.graphql.AbstractGraphqlQuery
import kotlinx.serialization.Serializable

@Serializable
data class CreateMomentMutationResult(
    val moment: IdResponse
)

@Serializable
data class CreateMomentMutationResponse(
    val createFeedMoment: MutationResponse<CreateMomentMutationResult>
)

class CreateMomentMutation(private val title: String, private val pictureUrl: String, private val parentMoment: String?) :
    AbstractGraphqlQuery<DataResponse<CreateMomentMutationResponse>>(
        DataResponse.serializer(CreateMomentMutationResponse.serializer())
    ) {
    override val variables: Map<String, Any> = mapOf(
        "pictureUrl" to pictureUrl,
        "title" to title
    )

    override val query: String
        get() = """
mutation(${'$'}pictureUrl: String!, ${'$'}title: String!) {
  createFeedMoment(pictureUrl: ${'$'}pictureUrl, title: ${'$'}title) {
    successful
    messages {
      code
      field
      message
    }
  	result {
        moment {
            id
        }
    }  
  }
}""".trimIndent()
}
