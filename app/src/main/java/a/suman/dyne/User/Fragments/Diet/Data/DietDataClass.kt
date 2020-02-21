package a.suman.dyne.User.Fragments.Diet.Data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class DietDataClass(
    @PrimaryKey
    val id:String,
    val Day:String,
    val Name:String,
    val Time:String
)