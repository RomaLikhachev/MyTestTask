package com.test.mytesttask.util

import com.test.mytesttask.model.Employee
import com.test.mytesttask.util.date.AgeCalculator
import com.test.mytesttask.util.date.DateFormatter
import com.test.mytesttask.util.name.NameFormatter
import javax.inject.Inject

/**
 * Contains all data handlers. Delegates raw data processing and returns data in the correct format.
 */
class EmployeeDataFormatHandler @Inject constructor(
    private val nameFormatter: NameFormatter,
    private val dateFormatter: DateFormatter,
    private val ageCalculator: AgeCalculator
) {

    fun handleData(employeeList: List<Employee>) {
        for (employee in employeeList) {
            employee.firstName = nameFormatter.format(employee.firstName)
            employee.lastName = nameFormatter.format(employee.lastName)
            employee.birthday = dateFormatter.format(employee.birthday)
            employee.age = ageCalculator.calculateAge(employee.birthday)
        }
    }
}