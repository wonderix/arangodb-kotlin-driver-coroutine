package com.arrangodb.kotlin

import com.arangodb.ArangoDBAsync
import com.arangodb.entity.*
import com.arangodb.model.LogOptions
import com.arangodb.model.UserCreateOptions
import com.arangodb.model.UserUpdateOptions
import com.arangodb.util.ArangoDeserializer
import com.arangodb.util.ArangoSerializer
import com.arangodb.velocypack.*
import kotlinx.coroutines.experimental.future.await
import java.io.InputStream
import javax.net.ssl.SSLContext


class ArangoDB(private val arangodb: ArangoDBAsync) {

    class Builder {
        private var builder = ArangoDBAsync.Builder()
        fun user(user: String): Builder {
            builder.user(user)
            return this
        }

        fun password(password: String): Builder {
            builder.password(password)
            return this
        }

        fun loadProperties(inputStream: InputStream?): Builder {
            builder.loadProperties(inputStream)
            return this
        }


        fun host(host: String, port: Int): Builder {
            builder.host(host,port)
            return this
        }

        fun timeout(timeout: Int?): Builder {
            builder.timeout(timeout)
            return this
        }

         fun useSsl(useSsl: Boolean?): Builder {
            builder.useSsl(useSsl)
            return this
        }

        fun sslContext(sslContext: SSLContext): Builder {
            builder.sslContext(sslContext)
            return this
        }

        fun chunksize(chunksize: Int?): Builder {
            builder.chunksize(chunksize)
            return this
        }

        fun maxConnections(maxConnections: Int?): Builder {
            builder.maxConnections(maxConnections)
            return this
        }

        fun <T> registerSerializer(clazz: Class<T>, serializer: VPackSerializer<T>): Builder {
            builder.registerSerializer(clazz,serializer)
            return this
        }

        fun <T> registerEnclosingSerializer(clazz: Class<T>, serializer: VPackSerializer<T>): Builder {
            builder.registerEnclosingSerializer(clazz,serializer)
            return this
        }

        fun <T> registerDeserializer(clazz: Class<T>, deserializer: VPackDeserializer<T>): Builder {
            builder.registerDeserializer(clazz, deserializer)
            return this
        }

        fun <T> registerInstanceCreator(clazz: Class<T>, creator: VPackInstanceCreator<T>): Builder {
            builder.registerInstanceCreator(clazz, creator)
            return this
        }

        fun registerJsonDeserializer(type: ValueType, deserializer: VPackJsonDeserializer): Builder {
            builder.registerJsonDeserializer(type, deserializer)
            return this
        }

        fun registerJsonDeserializer(attribute: String, type: ValueType, deserializer: VPackJsonDeserializer): Builder {
            builder.registerJsonDeserializer(attribute, type,deserializer)
            return this
        }

        fun <T> registerJsonSerializer(clazz: Class<T>, serializer: VPackJsonSerializer<T>): Builder {
            builder.registerJsonSerializer(clazz,serializer)
            return this
        }

        fun <T> registerJsonSerializer(attribute: String, clazz: Class<T>, serializer: VPackJsonSerializer<T>): Builder {
            builder.registerJsonSerializer(attribute,clazz,serializer)
            return this
        }

        fun <T : Annotation> annotationFieldFilter(type: Class<T>, fieldFilter: VPackAnnotationFieldFilter<T>): Builder {
            builder.annotationFieldFilter(type,fieldFilter)
            return this
        }

        fun <T : Annotation> annotationFieldNaming(type: Class<T>, fieldNaming: VPackAnnotationFieldNaming<T>): Builder {
            builder.annotationFieldNaming(type,fieldNaming)
            return this
        }

        fun registerModule(module: VPackModule): Builder {
            builder.registerModule(module)
            return this
        }

        fun registerModules(vararg modules: VPackModule): Builder {
            builder.registerModules(*modules)
            return this
        }

        fun registerJsonModule(module: VPackParserModule): Builder {
            builder.registerJsonModule(module)
            return this
        }

        fun registerJsonModules(vararg modules: VPackParserModule): Builder {
            builder.registerJsonModules(*modules)
            return this
        }

        fun setSerializer(serializer: ArangoSerializer): Builder {
            builder.setSerializer(serializer)
            return this
        }

        fun setDeserializer(deserializer: ArangoDeserializer): Builder {
            builder.setDeserializer(deserializer)
            return this
        }

        fun build() : ArangoDB = ArangoDB(builder.build())
    }

    suspend fun createDatabase(name: String): Boolean = arangodb.createDatabase(name).await()

    suspend fun db(name: String): Database = Database(arangodb.db(name))

    fun shutdown() = arangodb.shutdown()

    suspend fun db(): Database = Database(arangodb.db())

    suspend  fun getDatabases(): MutableCollection<String> = arangodb.databases.await()

    suspend fun getAccessibleDatabases(): MutableCollection<String> = arangodb.accessibleDatabases.await()

    suspend fun getAccessibleDatabasesFor(user: String): MutableCollection<String> = arangodb.getAccessibleDatabasesFor(user).await()

    suspend fun getVersion(): ArangoDBVersion = arangodb.version.await()

    suspend fun getRole(): ServerRole = arangodb.role.await()

    suspend fun createUser(user: String, passwd: String): UserEntity = arangodb.createUser(user,passwd).await()

    suspend fun createUser(user: String, passwd: String, options: UserCreateOptions): UserEntity = arangodb.createUser(user,passwd,options).await()

    suspend fun deleteUser(user: String): Void = arangodb.deleteUser(user).await()

    suspend fun getUser(user: String): UserEntity = arangodb.getUser(user).await()

    suspend fun getUsers(): MutableCollection<UserEntity> = arangodb.users.await()

    suspend fun updateUser(user: String, options: UserUpdateOptions): UserEntity = arangodb.updateUser(user,options).await()

    suspend fun replaceUser(user: String, options: UserUpdateOptions): UserEntity = arangodb.replaceUser(user,options).await()

    suspend fun getLogs(options: LogOptions): LogEntity = arangodb.getLogs(options).await()

    suspend fun getLogLevel(): LogLevelEntity = arangodb.logLevel.await()

    suspend fun setLogLevel(entity: LogLevelEntity): LogLevelEntity = arangodb.setLogLevel(entity).await()

}