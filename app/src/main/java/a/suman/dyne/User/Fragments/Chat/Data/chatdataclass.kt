package a.suman.dyne.User.Fragments.Chat.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class chatdataclass(
    @PrimaryKey
    var chatID: String,
    var trainerid:String,
    var trainern:String
)