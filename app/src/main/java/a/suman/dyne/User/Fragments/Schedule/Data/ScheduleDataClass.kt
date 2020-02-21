package a.suman.dyne.User.Fragments.Schedule.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ScheduleDataClass(
    @PrimaryKey
    val id:String,
    val Name:String,
    val Sets:String,
    val Reps:String,
    val VideoTitle:String,
    val VideoLink:String,
    val Day:String
)