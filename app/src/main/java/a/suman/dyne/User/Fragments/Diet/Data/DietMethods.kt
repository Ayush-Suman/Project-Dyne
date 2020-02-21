package a.suman.dyne.User.Fragments.Diet.Data


import androidx.room.*
import io.reactivex.Flowable

@Dao
interface DietMethods{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(DietDataList:MutableList<DietDataClass>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(DietList: MutableList<DietList>)

    @Query("Delete from DietDataClass")
    fun delete()

    @Query("Select * from DietDataClass where Day = :day")
    fun getdata(day:String): Flowable<List<DietDataClass>>

    @Query("Select * from DietList Where diet_id= :diet_id")
    fun getlist(diet_id:String):Flowable<List<DietList>>

}