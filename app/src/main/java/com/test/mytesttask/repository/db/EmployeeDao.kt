package com.test.mytesttask.repository.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.mytesttask.model.Employee

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(employees: List<Employee>)

    @Query("SELECT * FROM employee WHERE _id = :id")
    fun getEmployee(id: Int): LiveData<Employee>

    @Query("SELECT COUNT(*) FROM employee")
    fun hasEmployee(): Int

}