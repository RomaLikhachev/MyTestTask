package com.test.mytesttask.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

/**
 * Regulates many-to-many relationships by linking Room employees to their specialties
 */
@Entity(tableName = "employee_specialty_join",
    indices = arrayOf(Index(value = arrayOf("specialty_id"))),
    primaryKeys = arrayOf("employee_id", "specialty_id"),
    foreignKeys = arrayOf(
        ForeignKey(entity = Employee::class,
            parentColumns = arrayOf("_id"),
            childColumns = arrayOf("employee_id")),
        ForeignKey(entity = Specialty::class,
            parentColumns = arrayOf("_id"),
            childColumns = arrayOf("specialty_id"))
    )
)
data class EmployeeSpecialty(
    @ColumnInfo(name = "employee_id")
    var employeeId: Int, // specialty_id

    @ColumnInfo(name = "specialty_id")
    var specialtyId: Int
)