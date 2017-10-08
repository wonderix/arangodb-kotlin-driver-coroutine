package com.arrangodb.kotlin

import com.arangodb.ArangoEdgeCollectionAsync
import com.arangodb.entity.EdgeEntity
import com.arangodb.entity.EdgeUpdateEntity
import com.arangodb.model.*
import kotlinx.coroutines.experimental.future.await
import kotlin.reflect.KClass

class EdgeCollection internal constructor(private val edgeCollection: ArangoEdgeCollectionAsync) {

    suspend fun <T> insertEdge(value: T): EdgeEntity = edgeCollection.insertEdge(value).await()

    suspend fun <T> insertEdge(value: T, options: EdgeCreateOptions): EdgeEntity = edgeCollection.insertEdge(value,options).await()

    suspend fun <T: Any> getEdge(key: String, type: KClass<T>): T = edgeCollection.getEdge(key,type.java).await()

    suspend fun <T: Any> getEdge(key: String, type: KClass<T>, options: DocumentReadOptions): T = edgeCollection.getEdge(key,type.java,options).await()

    suspend fun <T> replaceEdge(key: String, value: T): EdgeUpdateEntity = edgeCollection.replaceEdge(key,value).await()

    suspend fun <T> replaceEdge(key: String, value: T, options: EdgeReplaceOptions): EdgeUpdateEntity = edgeCollection.replaceEdge(key,value,options).await()

    suspend fun <T> updateEdge(key: String, value: T): EdgeUpdateEntity = edgeCollection.updateEdge(key,value).await()

    suspend fun <T> updateEdge(key: String, value: T, options: EdgeUpdateOptions): EdgeUpdateEntity = edgeCollection.updateEdge(key,value,options).await()

    suspend fun deleteEdge(key: String): Void = edgeCollection.deleteEdge(key).await()

    suspend fun deleteEdge(key: String, options: EdgeDeleteOptions): Void = edgeCollection.deleteEdge(key,options).await()
}