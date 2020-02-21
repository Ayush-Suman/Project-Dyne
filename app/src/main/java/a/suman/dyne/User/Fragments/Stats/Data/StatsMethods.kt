package a.suman.dyne.User.Fragments.Stats.Data


import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface StatsMethods{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ScheduleDataList:StatsDataClass)

    @Query("Select * from StatsDataClass")
    fun getdata(): Flowable<StatsDataClass>

    @Query("Delete from StatsDataClass")
    fun delete()
}