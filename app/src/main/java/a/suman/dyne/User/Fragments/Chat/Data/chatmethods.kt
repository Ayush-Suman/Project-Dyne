package a.suman.dyne.User.Fragments.Chat.Data

import androidx.room.*
import io.reactivex.Flowable

@Dao
interface chatmethods{
    @Query("Select * from chatdataclass")
    fun getData():Flowable<List<chatdataclass>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(chatlist:MutableList<chatdataclass>)

    @Query("Delete from chatdataclass")
    fun delete()
}