package com.arrangodb.kotlin

import com.arangodb.ArangoCursor
import com.arangodb.ArangoDatabaseAsync
import com.arangodb.entity.*
import com.arangodb.model.*
import kotlinx.coroutines.experimental.future.await
import kotlin.reflect.KClass

class Database internal constructor(private val database: ArangoDatabaseAsync) {
    suspend fun createCollection(name: String): CollectionEntity = database.createCollection(name).await()

    fun collection(name: String): DocumentCollection = DocumentCollection(database.collection(name))

    suspend fun <T : Any> query(query: String, bindVars: Map<String, Any>, options: AqlQueryOptions?, type: KClass<T>): ArangoCursor<T> {
        return database.query(query, bindVars, options, type.java).await()
    }

    suspend fun getVersion(): ArangoDBVersion = database.version.await()

    suspend fun getAccessibleDatabases(): MutableCollection<String> = database.accessibleDatabases.await()

    suspend fun createCollection(name: String, options: CollectionCreateOptions): CollectionEntity = database.createCollection(name, options).await()

    suspend fun getCollections(): MutableCollection<CollectionEntity> = database.collections.await()

    suspend fun getCollections(options: CollectionsReadOptions): MutableCollection<CollectionEntity> = database.getCollections(options).await()

    suspend fun getIndex(id: String): IndexEntity = database.getIndex(id).await()

    suspend fun deleteIndex(id: String): String = database.deleteIndex(id).await()

    suspend fun drop(): Boolean = database.drop().await()

    suspend fun grantAccess(user: String): Void = database.grantAccess(user).await()

    suspend fun revokeAccess(user: String): Void = database.revokeAccess(user).await()

    suspend fun <T : Any> cursor(cursorId: String, type: KClass<T>): MutableIterator<Any> = database.cursor(cursorId, type.java).await()

    suspend fun explainQuery(query: String, bindVars: Map<String, Any>, options: AqlQueryExplainOptions): AqlExecutionExplainEntity = database.explainQuery(query, bindVars, options).await()

    suspend fun parseQuery(query: String): AqlParseEntity = database.parseQuery(query).await()

    suspend fun clearQueryCache(): Void = database.clearQueryCache().await()

    suspend fun getQueryCacheProperties(): QueryCachePropertiesEntity = database.queryCacheProperties.await()

    suspend fun setQueryCacheProperties(properties: QueryCachePropertiesEntity): QueryCachePropertiesEntity = database.setQueryCacheProperties(properties).await()

    suspend fun getQueryTrackingProperties(): QueryTrackingPropertiesEntity = database.queryTrackingProperties.await()

    suspend fun setQueryTrackingProperties(properties: QueryTrackingPropertiesEntity): QueryTrackingPropertiesEntity = database.setQueryTrackingProperties(properties).await()

    suspend fun getCurrentlyRunningQueries(): MutableCollection<QueryEntity> = database.currentlyRunningQueries.await()

    suspend fun getSlowQueries(): MutableCollection<QueryEntity> = database.slowQueries.await()

    suspend fun clearSlowQueries(): Void = database.clearSlowQueries().await()

    suspend fun killQuery(id: String): Void = database.killQuery(id).await()

    suspend fun createAqlFunction(name: String, code: String, options: AqlFunctionCreateOptions): Void = database.createAqlFunction(name, code, options).await()

    suspend fun deleteAqlFunction(name: String, options: AqlFunctionDeleteOptions): Void = database.deleteAqlFunction(name, options).await()

    suspend fun getAqlFunctions(options: AqlFunctionGetOptions): MutableCollection<AqlFunctionEntity> = database.getAqlFunctions(options).await()

    fun graph(name: String) =  Graph(database.graph(name))

    suspend fun createGraph(name: String, edgeDefinitions: kotlin.collections.Collection<EdgeDefinition>): GraphEntity = database.createGraph(name, edgeDefinitions).await()

    suspend fun createGraph(name: String, edgeDefinitions: kotlin.collections.Collection<EdgeDefinition>, options: GraphCreateOptions): GraphEntity = database.createGraph(name, edgeDefinitions, options).await()

    suspend fun getGraphs(): MutableCollection<GraphEntity> = database.graphs.await()

    suspend fun <T : Any> transaction(action: String, type: KClass<T>, options: TransactionOptions): T = database.transaction(action, type.java, options).await()

    suspend fun getInfo(): DatabaseEntity = database.info.await()

    suspend fun <V : Any, E : Any> executeTraversal(vertexClass: KClass<V>, edgeClass: KClass<E>, options: TraversalOptions): Any = database.executeTraversal(vertexClass.java, edgeClass.java, options).await()

    suspend fun <T : Any> getDocument(id: String, type: KClass<T>): T = database.getDocument(id, type.java).await()

    suspend fun <T : Any> getDocument(id: String, type: KClass<T>, options: DocumentReadOptions): T = database.getDocument(id, type.java, options).await()

    suspend fun reloadRouting(): Void = database.reloadRouting().await()
}