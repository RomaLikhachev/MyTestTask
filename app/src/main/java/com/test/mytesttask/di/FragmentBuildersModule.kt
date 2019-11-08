package com.test.mytesttask.di

import com.test.mytesttask.ui.employee.EmployeeFragment
import com.test.mytesttask.ui.profile.ProfileFragment
import com.test.mytesttask.ui.specialty.SpecialtyFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeSpecialtyFragment(): SpecialtyFragment

    @ContributesAndroidInjector
    abstract fun contributeEmployeeFragment(): EmployeeFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment
}