package com.test.mytesttask.util

import java.util.concurrent.atomic.AtomicInteger

/**
 * Generates and binds the id to the employee for the primary key in the database.
 */
object IdGenerator {
    private val atomicInteger = AtomicInteger()

    fun generateId() = atomicInteger.incrementAndGet()
}