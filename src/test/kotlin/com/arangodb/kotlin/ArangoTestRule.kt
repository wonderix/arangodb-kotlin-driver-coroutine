package com.arangodb.kotlin

import com.arrangodb.kotlin.Database
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.concurrent.ExecutionException
import org.junit.AfterClass
import com.arangodb.ArangoDBAsync
import org.junit.BeforeClass
import com.arangodb.ArangoDatabaseAsync
import com.arrangodb.kotlin.ArangoDB
import kotlinx.coroutines.experimental.runBlocking


class ArangoTestRule : TestRule {
    protected val TEST_DB = "kotlin_driver_test_db"
    private var databaseInner: Database? = null

    val database: Database
      get() = databaseInner!!

    override fun apply(statement: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                val arangoDB = ArangoDB.Builder().build()
                databaseInner = runBlocking {
                    try {
                        arangoDB.db(TEST_DB).drop()
                    } catch (e: Exception) {
                    }
                    arangoDB.createDatabase(TEST_DB)
                    arangoDB.db(TEST_DB)
                }
                statement.evaluate()
                runBlocking {
                    databaseInner!!.drop()
                    arangoDB.shutdown()
                }

            }

        }
    }

}

