package a.suman.dyne.User.Fragments.Fragment_home.Data

import a.suman.dyne.User.DatabaseDyne
import android.content.Context
import android.os.Handler
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers


class HomeRepository(context: Context){

        val firebaseAuth:FirebaseAuth = FirebaseAuth.getInstance()
        val firebaseStorage:FirebaseStorage = FirebaseStorage.getInstance()
        val firebaseFirestore:FirebaseFirestore = FirebaseFirestore.getInstance()
        private val homeMethods: HomeMethods=DatabaseDyne.getInstance(context).getHomeMethods()


    fun fetchGymData() {
    firebaseFirestore.collection("Gym").get().
        addOnCompleteListener{
            Thread{if(it.isSuccessful){
                Log.d("Thread", "New Thread created home")
                var listHomeData:MutableList<HomeDataClass> = mutableListOf()
                var i=0
                while (i<it.result!!.documents.size){

                var GymDataSnapshot=it.result!!.documents[i]
                listHomeData.add(HomeDataClass(GymDataSnapshot.id,
                    GymDataSnapshot["Description"].toString(),
                    GymDataSnapshot["Name"].toString(),
                    GymDataSnapshot["Ratings"].toString().toFloat(),
                    GymDataSnapshot["State"].toString(),
                    GymDataSnapshot["Town"].toString()))
                    i++
            }
             homeMethods.insert(listHomeData)
            }else{
                //TODO(Insert COMPLETABLE)
            }}.start()
            }

        }


    fun getGymData(): Flowable<List<HomeDataClass>>{
        Log.d("Observable", "Observable called")
        return homeMethods.getdata().subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread())
    }
}