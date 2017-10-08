package com.arangodb.kotlin

import kotlinx.coroutines.experimental.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.ClassRule
import org.junit.Test

class TestDocument(val name: String) {
    constructor() : this("empty")
}
class ArangoDatabaseTest {
    companion object {
        @ClassRule
        @JvmField
        val rule = ArangoTestRule()
    }


    @Test
    fun test() {
        runBlocking {
            rule.database.createCollection("test")
            val collection = rule.database.collection("test")
            val documentEntity = collection.insertDocument(TestDocument("test-document"))
            val document = collection.getDocument(documentEntity.key,TestDocument::class)
            assertThat(document.name , `is`("test-document"))
        }
    }
}