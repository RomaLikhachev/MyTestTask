package com.test.mytesttask.util.date

import org.threeten.bp.LocalDate
import org.threeten.bp.Period
import org.threeten.bp.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class AgeCalculator @Inject constructor() {
    private val nowDate = LocalDate.now()
    private val appDateFormat = DateTimeFormatter.ofPattern(APP_DATE_FORMAT, Locale.getDefault())

    fun calculateAge(birthday: String?): Int? {
        if (birthday == null || birthday.isEmpty()) return null

        val birthDate = LocalDate.parse(birthday, appDateFormat)
        return Period.between(birthDate, nowDate).years
    }
}