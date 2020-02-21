package a.suman.dyne.User.Fragments.Stats.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StatsDataClass(
    @PrimaryKey
    val id:String,
    val MaintenanceC:String,
    val C:String,
    val M4:Int,
    val M3:Int,
    val M2:Int,
    val M1:Int,
    val TrainersC:String,
val TrainersN:String
)