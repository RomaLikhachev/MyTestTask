package com.test.mytesttask.di

import android.content.Context
import androidx.room.Room
import com.test.mytesttask.repository.db.EmployeeDao
import com.test.mytesttask.repository.db.EmployeeDatabase
import com.test.mytesttask.repository.db.EmployeeSpecialtyJoinDao
import com.test.mytesttask.repository.db.SpecialtyDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Suppress("unused")
@Module
class DatabaseModule {

    companion object {
        private const val DB_NAME = "employee-database.db"
    }

    @Singleton
    @Provides
    fun provideEmployeeDatabase(appContext: Context): EmployeeDatabase {
        return Room
            .databaseBuilder(appContext, EmployeeDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideEmployeeDao(db: EmployeeDatabase): EmployeeDao {
        return db.employeeDao()
    }

    @Singleton
    @Provides
    fun provideSpecialtyDao(db: EmployeeDatabase): SpecialtyDao {
        return db.specialtyDao()
    }

    @Singleton
    @Provides
    fun provideEmployeeSpecialtyJoinDao(db: EmployeeDatabase): EmployeeSpecialtyJoinDao {
        return db.employeeSpecialtyJoinDao()
    }
}