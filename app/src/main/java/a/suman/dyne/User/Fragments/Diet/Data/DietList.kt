package a.suman.dyne.User.Fragments.Diet.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DietList(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    var diet_id:String,
    val Quantity:String,
    val FoodItem:String,
    val Carbohydrates:String,
    val Protein:String,
    val Fats:String
)