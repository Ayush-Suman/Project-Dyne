package a.suman.dyne.User.Fragments.Schedule.Data

import androidx.room.*
import io.reactivex.Flowable

@Dao
interface ScheduleMethods {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ScheduleDataList:MutableList<ScheduleDataClass>)

    @Query("Select * from ScheduleDataClass where Day= :day")
    fun getdata(day:String): Flowable<List<ScheduleDataClass>>

    @Query("Delete from ScheduleDataClass")
    fun delete()
}