package com.mirego.nwad.repositories.impl

import com.mirego.nwad.repositories.MomentRepository
import com.mirego.nwad.repositories.graphql.mutations.CreateMomentMutation
import com.mirego.trikot.graphql.GraphqlPublisherFactory
import com.mirego.trikot.streams.reactive.map
import com.mirego.trikot.streams.reactive.promise.Promise
import org.reactivestreams.Publisher

class MomentRepositoryImpl(private val graphqlPublisherFactory: GraphqlPublisherFactory): MomentRepository {
    override fun createMoment(title: String, pictureUrl: String, parentMoment: String?): Promise<Unit> {
        val query = graphqlPublisherFactory.create(CreateMomentMutation(title, pictureUrl, parentMoment))
        query.execute()
        return Promise.from(query.map { Unit })
    }
}