package com.example.testapp.medicineList

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestCoroutineRule : TestRule {
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                Dispatchers.setMain(testDispatcher)
                base.evaluate()
                Dispatchers.resetMain()
                testScope.cleanupTestCoroutines()
            }
        }
    }

    fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) {
        testDispatcher.runBlockingTest { block() }
    }
}
