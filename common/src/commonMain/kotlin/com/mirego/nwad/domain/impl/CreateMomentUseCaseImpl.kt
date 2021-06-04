package com.mirego.nwad.domain.impl

import com.mirego.nwad.domain.CreateMomentData
import com.mirego.nwad.domain.CreateMomentUseCase
import com.mirego.nwad.repositories.MomentRepository
import com.mirego.nwad.repositories.MomentsRepository
import com.mirego.nwad.repositories.UploadRepository
import com.mirego.trikot.streams.cancellable.CancellableManager
import com.mirego.trikot.streams.reactive.promise.Promise

class CreateMomentUseCaseImpl(private val uploadRepository: UploadRepository, private val momentRepository: MomentRepository): CreateMomentUseCase {
    override fun createNewMoment(createMomentData: CreateMomentData, cancellableManager: CancellableManager): Promise<Unit> {
        return uploadRepository.uploadPhoto(createMomentData.jpegImageContent, cancellableManager)
            .onSuccessReturn {
                momentRepository.createMoment(createMomentData.title, it, createMomentData.parentMoment)
            }
    }
}