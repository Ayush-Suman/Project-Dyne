package a.suman.dyne.User.Fragments.Stats.Data

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
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class StatsRepository(context: Context) {

    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
    val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    val statsMethods = DatabaseDyne.getInstance(context).getStatsMethods()

    fun fetchStatsData() {
        firebaseFirestore.collection("Users").document(firebaseAuth.uid.toString())
            .collection("Stats").get().addOnCompleteListener {
                Thread{if(it.isSuccessful){
                    d("Thread", "New Thread created Stats")
                    if (it.result!!.documents.size>0){
                val StatsDSnapShot = it.result!!.documents[0]
                var statsDataClass = StatsDataClass(
                    StatsDSnapShot.id,
                    StatsDSnapShot["Maintenance Calories"].toString(),
                    StatsDSnapShot["Calories"].toString(),
                    Integer.parseInt(StatsDSnapShot["Month4"].toString()),
                    Integer.parseInt(StatsDSnapShot["Month3"].toString()),
                    Integer.parseInt(StatsDSnapShot["Month2"].toString()),
                    Integer.parseInt(StatsDSnapShot["Month1"].toString()),
                    StatsDSnapShot["Trainers Comment"].toString(),
                StatsDSnapShot["Trainers Name"].toString()
                )

                statsMethods.insert(statsDataClass)
                }}else{
                    //TODO(Insert COMPLETABLE)
                }}.start()

        }
    }

    fun getStatsData(): Flowable<StatsDataClass> {
        d("Observable", "Observable called")
        return statsMethods.getdata().subscribeOn(Schedulers.io())
    }
}
