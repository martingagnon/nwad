package com.mirego.nwad.repositories.impl

import com.mirego.nwad.models.SignedUrl
import com.mirego.nwad.repositories.UploadRepository
import com.mirego.nwad.repositories.graphql.mutations.GenerateSignedUrlMutation
import com.mirego.trikot.graphql.GraphqlPublisherFactory
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
    ): Promise<Unit> {
        val filename = "Filename"

        return Promise.from(
            generateSignedUrl(filename, cancellableManager)
                .map { signedUrl ->
                    val uploadUrl = signedUrl.signedUrl
                    // TODO Upload using HTTPRequest and Create body
                    Unit
                },
            cancellableManager
        )
    }

    private fun generateSignedUrl(filename: String, cancellableManager: CancellableManager): Promise<SignedUrl> {
        val mutation = GenerateSignedUrlMutation(filename)
        val request = graphqlPublisherFactory.create(mutation)
        cancellableManager.add(request)
        request.execute()
        return Promise.from(
            request
                .map {
                    it.data.generateSignedUrl.result ?: throw StreamsProcessorException("Unable to create signed url")
                }
        )
    }
}
