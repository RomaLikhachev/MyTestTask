package com.test.mytesttask.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.test.mytesttask.util.IdGenerator

@Entity
data class Employee(
    @PrimaryKey
    @ColumnInfo(name = "_id")
    var id: Int,

    @ColumnInfo(name = "first_name")
    @SerializedName("f_name")
    @Expose
    var firstName: String,

    @ColumnInfo(name = "last_name")
    @SerializedName("l_name")
    @Expose
    var lastName: String,

    @ColumnInfo(name = "birthday")
    @SerializedName("birthday")
    @Expose
    var birthday: String?,

    @ColumnInfo(name = "age")
    var age: Int?,

    /**
     * Room: The list of specialties is ignored when getting from the database, the link is dynamic.
     * Retrofit: The list of specialties is initialized when parsing the Json response.
     */
    @Ignore
    @SerializedName("specialty")
    @Expose
    var specialtyList: List<Specialty>
) {

    companion object {
        const val UNKNOWN_ID = -1
        const val UNKNOWN_AGE = 0
    }

    @Ignore
    val compositeTitle = lazy {
        createCompositeTitle()
    }

    /** Standard constructor for Room access, support for Ignore annotations */
    constructor() : this(UNKNOWN_ID, "", "", null, null, emptyList())

    /** Dynamic generation ID, for proper operation of foreign keys Room (SpecialtyEmployee) */
    init {
        if (id == UNKNOWN_ID) {
            id = IdGenerator.generateId()
        }
    }

    fun generateEmployeeSpecialtyList(): List<EmployeeSpecialty> {
        return specialtyList.mapTo(arrayListOf()) {
            EmployeeSpecialty(employeeId = id, specialtyId = it.id)
        }
    }

    /**
     * Display custom data in [EmployeeFragment]
     */
    private fun createCompositeTitle(): String? {
        return buildString {
            append(firstName)
            append(" ")
            append(lastName)

            if (age != null && age != UNKNOWN_AGE) {
                append(", ")
                append(age.toString())
            }

            toString()
        }
    }
}