package a.suman.dyne.User.Fragments.Fragment_home.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HomeDataClass(
    @PrimaryKey
    val id:String,
    val name:String,
    val desc:String,
    val rating:Float,
    val state:String,
    val town:String

)