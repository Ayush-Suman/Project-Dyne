package a.suman.dyne.User.Fragments.Fragment_home.Data

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Observable


@Dao
interface HomeMethods{
@Insert(onConflict = OnConflictStrategy.REPLACE)
fun insert(GymDataList:MutableList<HomeDataClass>)

@Query("Select * from HomeDataClass")
fun getdata(): Flowable<List<HomeDataClass>>

    @Query("Delete from HomeDataClass")
    fun delete()
}