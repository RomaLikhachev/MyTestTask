package com.test.mytesttask.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.mytesttask.model.Employee
import com.test.mytesttask.model.EmployeeSpecialty
import com.test.mytesttask.model.Specialty

@Database(entities = [Employee::class, Specialty::class, EmployeeSpecialty::class],
    version = 1,
    exportSchema = false
)
abstract class EmployeeDatabase : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

    abstract fun specialtyDao(): SpecialtyDao

    abstract fun employeeSpecialtyJoinDao(): EmployeeSpecialtyJoinDao

}