package com.arrangodb.kotlin

import com.arangodb.ArangoGraphAsync
import com.arangodb.entity.EdgeDefinition
import com.arangodb.entity.GraphEntity
import kotlinx.coroutines.experimental.future.await

class Graph internal constructor(private val graph: ArangoGraphAsync) {

    suspend fun drop(): Void = graph.drop().await()

    suspend fun getInfo(): GraphEntity = graph.info.await()

    suspend fun getVertexCollections(): MutableCollection<String> = graph.vertexCollections.await()

    suspend fun addVertexCollection(name: String): GraphEntity = graph.addVertexCollection(name).await()

    fun vertexCollection(name: String) = VertexCollection(graph.vertexCollection(name))

    fun edgeCollection(name: String) = EdgeCollection(graph.edgeCollection(name))

    suspend fun getEdgeDefinitions(): MutableCollection<String> = graph.edgeDefinitions.await()

    suspend fun addEdgeDefinition(definition: EdgeDefinition): GraphEntity = graph.addEdgeDefinition(definition).await()

    suspend fun replaceEdgeDefinition(definition: EdgeDefinition): GraphEntity = graph.replaceEdgeDefinition(definition).await()

    suspend fun removeEdgeDefinition(definitionName: String): GraphEntity = graph.removeEdgeDefinition(definitionName).await()

}