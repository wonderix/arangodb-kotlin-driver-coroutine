package com.arrangodb.kotlin

import com.arangodb.ArangoVertexCollectionAsync
import com.arangodb.entity.VertexEntity
import com.arangodb.entity.VertexUpdateEntity
import com.arangodb.model.*
import kotlinx.coroutines.experimental.future.await
import kotlin.reflect.KClass

class VertexCollection internal constructor(private val vertexCollection: ArangoVertexCollectionAsync) {

    suspend fun drop(): Void = vertexCollection.drop().await()

    suspend fun <T> insertVertex(value: T): VertexEntity = vertexCollection.insertVertex(value).await()

    suspend fun <T> insertVertex(value: T, options: VertexCreateOptions): VertexEntity = vertexCollection.insertVertex(value,options).await()

    suspend fun <T : Any> getVertex(key: String, type: KClass<T>): T = vertexCollection.getVertex(key,type.java).await()

    suspend fun <T: Any> getVertex(key: String, type: KClass<T>, options: DocumentReadOptions): T = vertexCollection.getVertex(key,type.java,options).await()

    suspend fun <T> replaceVertex(key: String, value: T): VertexUpdateEntity = vertexCollection.replaceVertex(key,value).await()

    suspend fun <T> replaceVertex(key: String, value: T, options: VertexReplaceOptions): VertexUpdateEntity = vertexCollection.replaceVertex(key,value,options).await()

    suspend fun <T> updateVertex(key: String, value: T): VertexUpdateEntity = vertexCollection.updateVertex(key,value).await()

    suspend fun <T> updateVertex(key: String, value: T, options: VertexUpdateOptions): VertexUpdateEntity = vertexCollection.updateVertex(key,value,options).await()

    suspend fun deleteVertex(key: String): Void = vertexCollection.deleteVertex(key).await()

    suspend fun deleteVertex(key: String, options: VertexDeleteOptions): Void = vertexCollection.deleteVertex(key,options).await()

}