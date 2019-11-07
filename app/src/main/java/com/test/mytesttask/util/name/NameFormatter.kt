package com.test.mytesttask.util.name

import android.annotation.SuppressLint
import com.test.mytesttask.util.Formatter
import javax.inject.Inject

/**
 * Formats the name value to the correct format
 */
class NameFormatter @Inject constructor() : Formatter<String, String> {

    companion object {
        private const val VALID_NAME_REGEX = "\\p{Lu}{1}\\p{Ll}*"
    }

    /**
     * A double name check is performed, after which the formatting method is called
     */
    override fun format(value: String): String {
        if (value.isEmpty()) return value

        // Check for a double name
        if (value.contains("-") || value.contains(" ")) {
            // Define a name separator
            val separator = if (value.contains("-")) "-" else " "

            // Format name
            val namePartArray = value.split(separator).toTypedArray()
            for (i in namePartArray.indices) {
                namePartArray[i] = formatName(namePartArray[i].trim())
            }

            return namePartArray.joinToString(separator)
        } else {
            return formatName(value)
        }
    }

    /**
     * The need for formatting is determined, and then the value is formatted
     */
    @SuppressLint("DefaultLocale")
    private fun formatName(_name: String): String {
        val name = _name.trim()

        if (isFormatName(name)) return name

        return buildString {
            append(name.substring(0, 1).toUpperCase())
            append(name.substring(1).toLowerCase())
            toString()
        }
    }

    private fun isFormatName(name: String): Boolean {
        return name.matches(VALID_NAME_REGEX.toRegex())
    }
}