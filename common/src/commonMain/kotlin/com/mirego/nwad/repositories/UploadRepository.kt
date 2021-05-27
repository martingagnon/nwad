package com.mirego.nwad.repositories

import com.mirego.trikot.streams.cancellable.CancellableManager
import com.mirego.trikot.streams.reactive.promise.Promise

interface UploadRepository {
    fun uploadPhoto(data: ByteArray, cancellableManager: CancellableManager): Promise<Unit>
}
