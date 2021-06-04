package com.mirego.nwad.repositories.impl

import com.mirego.nwad.repositories.UploadRepository
import com.mirego.nwad.repositories.graphql.mutations.GenerateDirectUploadPayloadMutation
import com.mirego.trikot.graphql.GraphqlPublisherFactory
import com.mirego.trikot.http.HttpMethod
import com.mirego.trikot.streams.cancellable.CancellableManager
import com.mirego.trikot.streams.reactive.StreamsProcessorException
import com.mirego.trikot.streams.reactive.map
import com.mirego.trikot.streams.reactive.promise.Promise

class UploadRepositoryImpl(
    private val graphqlPublisherFactory: GraphqlPublisherFactory
) : UploadRepository {
    override fun uploadPhoto(
        data: ByteArray,
        cancellableManager: CancellableManager
    ): Promise<String> {
        val filename = "Filename"

        return Promise.from(
            generateSignedUrl(filename, cancellableManager),
            cancellableManager
        ).onSuccessReturn { signedUrl ->
            val uploadRequest = object: com.mirego.trikot.http.requestPublisher.HttpRequestPublisher<kotlin.Unit>() {
                override val builder = com.mirego.trikot.http.RequestBuilder().apply {
                    body = data
                    method = HttpMethod.POST
                    baseUrl = signedUrl
                }

                override fun processResponse(response: com.mirego.trikot.http.HttpResponse) {
                    if (response.statusCode >= 200 && response.statusCode < 300) {
                        dispatchSuccess(kotlin.Unit)
                    } else {
                        dispatchError(kotlin.IllegalStateException("Upload Status code - ${response.statusCode}"))
                    }
                }
            }

            uploadRequest.execute()
            Promise.from(uploadRequest, cancellableManager)
                .onSuccessReturn { Promise.resolve(signedUrl) }
        }
    }

    private fun generateSignedUrl(filename: String, cancellableManager: CancellableManager): Promise<String> {
        val mutation = GenerateDirectUploadPayloadMutation(filename)
        val request = graphqlPublisherFactory.create(mutation)
        cancellableManager.add(request)
        request.execute()
        return Promise.from(
            request
                .map {
                    it.data.generateDirectUploadPayload.result?.signedUrl ?: throw StreamsProcessorException("Unable to create signed url")
                }
        )
    }
}
