package a.suman.dyne.User.Fragments.Schedule.Data

import a.suman.dyne.User.DatabaseDyne
import android.content.Context
import android.os.AsyncTask
import android.os.Handler
import android.util.Log
import android.util.Log.d
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class ScheduleRepository (context: Context){

    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
    val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    val scheduleMethods=DatabaseDyne.getInstance(context).getScheduleMethods()

    fun fetchScheduleData(){
          firebaseFirestore.collection("Users").document(firebaseAuth.uid.toString()).
            collection("Schedule").get().
            addOnCompleteListener{
                Thread{
                    if(it.isSuccessful) {
                        d("Thread", "New Thread created Schedule ${it.result!!.documents}")
                        var ScheduleList: MutableList<ScheduleDataClass> = mutableListOf()
                        var i = 0
                        while (i < it.result!!.documents.size) {
                            var ScheduleDataSnapshot = it.result!!.documents[i]
                            val scheduleDataClass = ScheduleDataClass(
                                ScheduleDataSnapshot.id,
                                ScheduleDataSnapshot["Name"].toString(),
                                ScheduleDataSnapshot["Sets"].toString(),
                                ScheduleDataSnapshot["Reps"].toString(),
                                ScheduleDataSnapshot["VideoTitle"].toString(),
                                ScheduleDataSnapshot["VideoLink"].toString(),
                                ScheduleDataSnapshot["Day"].toString()
                            )
                            i++
                            d("adding", "Adding to List ${ScheduleList.size}")
                            ScheduleList.add(scheduleDataClass)
                        }

                        d("Inserting", "Schedule ${ScheduleList.size}")
                        scheduleMethods.insert(ScheduleList)
                    }else{
                            //TODO(Insert COMPLETABLE)
                        }}.start() }

    }

    fun getScheduleData(day:String):Flowable<List<ScheduleDataClass>>{
        d("Observable", "Observable called")
        return scheduleMethods.getdata(day).subscribeOn(Schedulers.io())
    }

}
