package com.mirego.nwad.repositories.datasources

// import com.mirego.trikot.datasources.BaseDataSource
// import com.mirego.trikot.datasources.DataSourceRequest
// import com.mirego.trikot.http.HttpConfiguration
// import com.mirego.trikot.streams.cancellable.CancellableManager
// import com.mirego.trikot.streams.reactive.StreamsProcessorException
// import com.mirego.trikot.streams.reactive.executable.BaseExecutablePublisher
// import com.mirego.trikot.streams.reactive.executable.ExecutablePublisher
// import kotlinx.serialization.KSerializer
// import kotlinx.serialization.json.Json

// class StorageDataSource<R : DataSourceRequest, T>(
//     private val fileManager: CommonFileManager,
//     private val deserializer: KSerializer<T>
// ) : BaseDataSource<R, T>() {
//     override fun save(
//         request: R,
//         data: T?
//     ) {
//         data?.let {
//             val json = HttpConfiguration.json.encodeToJsonElement(deserializer, data).toString()
//             val bytes = json.encodeToByteArray()
//             fileManager.saveFile(request.cacheableId.toString(), bytes).first()
//                 .subscribe(CancellableManager()) {
//                     read(request)
//                 }
//         }
//     }
//
//     override fun delete(cacheableId: Any) {
//         fileManager.deleteFile(cacheableId.toString())
//     }
//
//     override fun internalRead(request: R): ExecutablePublisher<T> {
//         return object : BaseExecutablePublisher<T>() {
//             override fun internalRun(cancellableManager: CancellableManager) {
//                 fileManager.readFile(request.cacheableId.toString())
//                     .map {
//                         try {
//                             Json.decodeFromString(deserializer, it.decodeToString())
//                         } catch (exception: Exception) {
//                             fileManager.deleteFile(request.cacheableId.toString())
//                             throw StreamsProcessorException(exception.message)
//                         }
//                     }
//                     .subscribe(cancellableManager,
//                         onNext = {
//                             dispatchSuccess(it)
//                         },
//                         onError = {
//                             dispatchError(it)
//                         }
//                     )
//             }
//         }
//     }
// }