package a.suman.dyne.User.Fragments.Profile.Data


import androidx.room.*
import io.reactivex.Flowable

@Dao
interface ProfileMethods {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profile:ProfileDataClass)

    @Query("Select * from ProfileDataClass")
    fun getdata(): Flowable<ProfileDataClass>

    @Query("Delete from ProfileDataClass")
    fun delete()
}