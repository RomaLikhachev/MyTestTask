package com.test.mytesttask.repository

import androidx.lifecycle.LiveData
import com.test.mytesttask.model.Employee
import com.test.mytesttask.model.Specialty

interface Repository {

    fun getSpecialties(): LiveData<List<Specialty>>

    fun getEmployeesForSpecialty(specialtyId: Int): LiveData<List<Employee>>

    fun getEmployee(employeeId: Int): LiveData<Employee>

    fun getSpecialtiesForEmployee(employeeId: Int): LiveData<List<Specialty>>
}