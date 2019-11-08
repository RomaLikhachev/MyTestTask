package com.test.mytesttask.ui.profile

import androidx.lifecycle.ViewModel
import com.test.mytesttask.repository.EmployeeRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    val repository: EmployeeRepository
) : ViewModel() {

}
