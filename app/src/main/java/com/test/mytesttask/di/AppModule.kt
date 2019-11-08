package com.test.mytesttask.di

import android.content.Context
import com.test.mytesttask.MainApplication
import com.test.mytesttask.repository.EmployeeRepository
import com.test.mytesttask.repository.db.EmployeeDatabase
import com.test.mytesttask.repository.service.EmployeeWebservice
import com.test.mytesttask.util.EmployeeDataFormatHandler
import com.test.mytesttask.util.date.AgeCalculator
import com.test.mytesttask.util.date.DateFormatter
import com.test.mytesttask.util.name.NameFormatter
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Suppress("unused")
@Module(includes = [DatabaseModule::class, NetworkModule::class])
class AppModule {

    @Provides
    fun provideAppContext(application: MainApplication): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideEmployeeRepository(
        service: EmployeeWebservice,
        db: EmployeeDatabase,
        dataFormatHandler : EmployeeDataFormatHandler,
        executor: Executor
    ) : EmployeeRepository {
        return EmployeeRepository(service, db, dataFormatHandler, executor)
    }

    @Singleton
    @Provides
    fun provideEmployeeDataFormatHandler(nameFormatter: NameFormatter,
                                         dateFormatter: DateFormatter,
                                         ageCalculator: AgeCalculator
    ): EmployeeDataFormatHandler {
        return EmployeeDataFormatHandler(nameFormatter, dateFormatter, ageCalculator)
    }

    @Singleton
    @Provides
    fun provideExecutor(): Executor {
        return Executors.newCachedThreadPool()
    }
}