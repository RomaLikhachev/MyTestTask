package com.test.mytesttask.util.date

import com.test.mytesttask.util.Formatter
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.TemporalAccessor
import java.text.ParseException
import java.util.*
import java.util.regex.Pattern
import javax.inject.Inject

/**
 * Defines the primary date format, after it parses into a date object
 * and formats dates to the general view of the application
 */
class DateFormatter @Inject constructor() : Formatter<String?, String?> {
    /** Represent different date formats from values */
    private val firstYearDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
    private val lastYearDateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.getDefault())
    /** Correct date format */
    private val newDateFormat = DateTimeFormatter.ofPattern(APP_DATE_FORMAT, Locale.getDefault())

    /** Regular expressions for determining the format of dates from the network */
    private val firstYearRegexPattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}")
    private val lastYearRegexPattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4}")

    /**
     * Defines a date format and delegates the formatting of this date to a special method
     */
    @Throws(ParseException::class)
    override fun format(value: String?): String? {
        return if (value == null || value.isEmpty()) {
            // An empty string will be translated as null, for more accurate display in the db
            // and work with the UI
            null
        } else if (value.matches(firstYearRegexPattern.pattern().toRegex())) {
            formatDate(firstYearDateFormat.parse(value))
        } else if (value.matches(lastYearRegexPattern.pattern().toRegex())) {
            formatDate(lastYearDateFormat.parse(value))
        } else {
            value
        }
    }

    private fun formatDate(date: TemporalAccessor): String {
        return newDateFormat.format(date)
    }
}