package com.test.mytesttask.ui.employee

import androidx.lifecycle.ViewModel
import com.test.mytesttask.repository.EmployeeRepository
import javax.inject.Inject

class EmployeeViewModel @Inject constructor(
    val repository: EmployeeRepository
) : ViewModel() {

}