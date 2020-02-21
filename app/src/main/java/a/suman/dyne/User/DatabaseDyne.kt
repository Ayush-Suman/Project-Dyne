package a.suman.dyne.User

import a.suman.dyne.User.Fragments.Chat.Data.chatdataclass
import a.suman.dyne.User.Fragments.Chat.Data.chatmethods
import a.suman.dyne.User.Fragments.Diet.Data.DietDataClass
import a.suman.dyne.User.Fragments.Diet.Data.DietList
import a.suman.dyne.User.Fragments.Diet.Data.DietMethods
import a.suman.dyne.User.Fragments.Fragment_home.Data.HomeDataClass
import a.suman.dyne.User.Fragments.Fragment_home.Data.HomeMethods
import a.suman.dyne.User.Fragments.Profile.Data.ProfileDataClass
import a.suman.dyne.User.Fragments.Profile.Data.ProfileMethods
import a.suman.dyne.User.Fragments.Schedule.Data.ScheduleDataClass
import a.suman.dyne.User.Fragments.Schedule.Data.ScheduleMethods
import a.suman.dyne.User.Fragments.Stats.Data.StatsDataClass
import a.suman.dyne.User.Fragments.Stats.Data.StatsMethods
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [HomeDataClass::class, DietDataClass::class, DietList::class, ProfileDataClass::class, ScheduleDataClass::class, StatsDataClass::class, chatdataclass::class], version = 1)
abstract class DatabaseDyne :RoomDatabase(){
    abstract fun getHomeMethods(): HomeMethods
    abstract fun getDietDMethods(): DietMethods
    abstract fun getProfileMethods(): ProfileMethods
    abstract fun getScheduleMethods(): ScheduleMethods
    abstract fun getStatsMethods(): StatsMethods
    abstract fun getchatmethods():chatmethods


    companion object{
        @Volatile
        var INSTANCE:DatabaseDyne?= null
            fun getInstance(context:Context):DatabaseDyne{

                   val instance = databaseBuilder(
                    context.applicationContext,
                    DatabaseDyne::class.java,
                "Dyne").
                    build()
                    INSTANCE=instance
                    return instance
        }
    }
}