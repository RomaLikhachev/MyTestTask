package com.test.mytesttask.repository.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.mytesttask.model.Employee
import com.test.mytesttask.model.EmployeeSpecialty
import com.test.mytesttask.model.Specialty

@Dao
interface EmployeeSpecialtyJoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(employeeSpecialtySet: Set<EmployeeSpecialty>)

    @Query("""
           SELECT employee.* FROM employee
           LEFT JOIN employee_specialty_join
           ON employee._id = employee_specialty_join.employee_id
           WHERE employee_specialty_join.specialty_id = :specialtyId
           """)
    fun getEmployeesForSpecialty(specialtyId: Int): LiveData<List<Employee>>

    @Query("""
           SELECT specialty.* FROM specialty
           LEFT JOIN employee_specialty_join
           ON specialty._id = employee_specialty_join.specialty_id
           WHERE employee_specialty_join.employee_id= :employeeId
           """)
    fun getSpecialtiesForEmployee(employeeId: Int): LiveData<List<Specialty>>

}