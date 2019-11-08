package com.test.mytesttask.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.mytesttask.ui.employee.EmployeeViewModel
import com.test.mytesttask.ui.profile.ProfileViewModel
import com.test.mytesttask.ui.specialty.SpecialtyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SpecialtyViewModel::class)
    abstract fun bindSpecialtyViewModel(specialtyViewModel: SpecialtyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EmployeeViewModel::class)
    abstract fun bindEmployeeViewModel(employeeViewModel: EmployeeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory):
            ViewModelProvider.Factory
}