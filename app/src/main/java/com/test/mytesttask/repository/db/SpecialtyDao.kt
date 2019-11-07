package com.test.mytesttask.repository.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.mytesttask.model.Specialty

@Dao
interface SpecialtyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(specialtySet: Set<Specialty>)

    @Query("SELECT * FROM specialty")
    fun getAll(): LiveData<List<Specialty>>

}