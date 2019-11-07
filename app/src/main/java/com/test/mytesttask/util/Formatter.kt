package com.test.mytesttask.util

import java.text.ParseException

interface Formatter<T, R> {
    @Throws(ParseException::class)
    fun format(value: T): R
}