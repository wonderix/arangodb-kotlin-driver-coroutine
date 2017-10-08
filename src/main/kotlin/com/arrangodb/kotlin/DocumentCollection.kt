package com.arrangodb.kotlin

import com.arangodb.ArangoCollectionAsync
import com.arangodb.entity.*
import com.arangodb.model.*
import kotlinx.coroutines.experimental.future.await
import kotlin.reflect.KClass

class DocumentCollection internal constructor(private val collection: ArangoCollectionAsync) {
    suspend fun <T> insertDocument(document: T): DocumentEntity = collection.insertDocument(document).await()


    suspend fun <T> insertDocument(value: T, options: DocumentCreateOptions): DocumentEntity = collection.insertDocument(value,options).await()

    suspend fun <T> insertDocuments(values: Collection<T>): MultiDocumentEntity<DocumentCreateEntity<T>> = collection.insertDocuments(values).await()

    suspend fun <T> insertDocuments(values: Collection<T>, options: DocumentCreateOptions?): MultiDocumentEntity<DocumentCreateEntity<T>> = collection.insertDocuments(values,options).await()

    suspend fun importDocuments(values: Collection<*>): DocumentImportEntity = collection.importDocuments(values).await()

    suspend fun importDocuments(values: Collection<*>, options: DocumentImportOptions): DocumentImportEntity = collection.importDocuments(values,options).await()

    suspend fun importDocuments(values: String): DocumentImportEntity = collection.importDocuments(values).await()

    suspend fun importDocuments(values: String, options: DocumentImportOptions): DocumentImportEntity = collection.importDocuments(values,options).await()

    suspend fun <T: Any> getDocument(key: String, type: KClass<T>): T = collection.getDocument(key,type.java).await()

    suspend fun <T: Any> getDocument(key: String, type: KClass<T>, options: DocumentReadOptions): T = collection.getDocument(key,type.java, options).await()

    suspend fun <T> replaceDocument(key: String, value: T): DocumentEntity = collection.replaceDocument(key,value).await()

    suspend fun <T> replaceDocument(key: String, value: T, options: DocumentReplaceOptions): DocumentEntity = collection.replaceDocument(key,value,options).await()

    suspend fun <T> replaceDocuments(values: Collection<T>): MultiDocumentEntity<DocumentUpdateEntity<T>> = collection.replaceDocuments(values).await()

    suspend fun <T> replaceDocuments(values: Collection<T>, options: DocumentReplaceOptions?): MultiDocumentEntity<DocumentUpdateEntity<T>> = collection.replaceDocuments(values,options).await()

    suspend fun <T> updateDocument(key: String, value: T): DocumentEntity = collection.updateDocument(key,value).await()

    suspend fun <T> updateDocument(key: String, value: T, options: DocumentUpdateOptions): DocumentEntity = collection.updateDocument(key,value,options).await()

    suspend fun <T> updateDocuments(values: Collection<T>): MultiDocumentEntity<DocumentUpdateEntity<T>> = collection.updateDocuments(values).await()

    suspend fun <T> updateDocuments(values: Collection<T>, options: DocumentUpdateOptions?): MultiDocumentEntity<DocumentUpdateEntity<T>> = collection.updateDocuments(values,options).await()

    suspend fun deleteDocument(key: String): DocumentDeleteEntity<Void> = collection.deleteDocument(key).await()

    suspend fun <T: Any> deleteDocument(key: String, type: KClass<T>, options: DocumentDeleteOptions): DocumentEntity = collection.deleteDocument(key,type.java,options).await()

    suspend fun deleteDocuments(values: Collection<*>): MultiDocumentEntity<DocumentDeleteEntity<Void>> = collection.deleteDocuments(values).await()

    suspend fun <T: Any> deleteDocuments(values: Collection<*>, type: KClass<T>, options: DocumentDeleteOptions): MultiDocumentEntity<DocumentDeleteEntity<T>> = collection.deleteDocuments(values,type.java,options).await()

    suspend fun documentExists(key: String): Boolean = collection.documentExists(key).await()

    suspend fun documentExists(key: String, options: DocumentExistsOptions): Boolean = collection.documentExists(key,options).await()

    suspend fun getIndex(id: String): IndexEntity = collection.getIndex(id).await()

    suspend fun deleteIndex(id: String): String = collection.deleteIndex(id).await()

    suspend fun createHashIndex(fields: Collection<String>, options: HashIndexOptions): IndexEntity = collection.createHashIndex(fields,options).await()

    suspend fun createSkiplistIndex(fields: Collection<String>, options: SkiplistIndexOptions): IndexEntity = collection.createSkiplistIndex(fields,options).await()

    suspend fun createPersistentIndex(fields: Collection<String>, options: PersistentIndexOptions): IndexEntity = collection.createPersistentIndex(fields,options).await()

    suspend fun createGeoIndex(fields: Collection<String>, options: GeoIndexOptions): IndexEntity = collection.createGeoIndex(fields,options).await()

    suspend fun createFulltextIndex(fields: Collection<String>, options: FulltextIndexOptions): IndexEntity = collection.createFulltextIndex(fields,options).await()

    suspend fun getIndexes(): MutableCollection<IndexEntity> = collection.indexes.await()

    suspend fun truncate(): CollectionEntity = collection.truncate().await()

    suspend fun count(): CollectionPropertiesEntity = collection.count().await()

    suspend fun drop(): Void = collection.drop().await()

    suspend fun drop(isSystem: Boolean): Void = collection.drop(isSystem).await()

    suspend fun load(): CollectionEntity = collection.load().await()

    suspend fun unload(): CollectionEntity = collection.unload().await()

    suspend fun getInfo(): CollectionEntity = collection.info.await()

    suspend fun getProperties(): CollectionPropertiesEntity = collection.properties.await()

    suspend fun changeProperties(options: CollectionPropertiesOptions): CollectionPropertiesEntity = collection.changeProperties(options).await()

    suspend fun rename(newName: String): CollectionEntity = collection.rename(newName).await()

    suspend fun getRevision(): CollectionRevisionEntity = collection.revision.await()
}