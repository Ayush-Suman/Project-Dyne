package a.suman.dyne.User.Fragments.Profile.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProfileDataClass(
    @PrimaryKey
    val Email:String,
    val Name:String,
    val Mobile:String,
    val Gender:String,
    val Weight:String,
    val Height:String
)