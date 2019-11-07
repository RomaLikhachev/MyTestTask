package com.test.mytesttask.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Specialty(
    @PrimaryKey
    @ColumnInfo(name = "_id")
    @SerializedName("specialty_id")
    var id: Int,

    @SerializedName("name")
    var name: String
)