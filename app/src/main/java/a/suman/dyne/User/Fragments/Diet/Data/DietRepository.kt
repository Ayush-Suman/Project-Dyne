package a.suman.dyne.User.Fragments.Fragment_home.Data

import a.suman.dyne.User.DatabaseDyne
import a.suman.dyne.User.Fragments.Diet.Data.DietDataClass
import a.suman.dyne.User.Fragments.Diet.Data.DietList
import a.suman.dyne.User.Fragments.Diet.Data.DietMethods
import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Handler
import android.util.Log.d
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers


class DietRepository(context: Context){

    val firebaseAuth:FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseStorage:FirebaseStorage = FirebaseStorage.getInstance()
    val firebaseFirestore:FirebaseFirestore = FirebaseFirestore.getInstance()
    private val dietMethods: DietMethods =DatabaseDyne.getInstance(context).getDietDMethods()


    fun fetchDietData() {
        firebaseFirestore.collection("Users").document(firebaseAuth.uid.toString()).collection("Diet").get().
            addOnCompleteListener{
                Thread{
                    if(it.isSuccessful){
                    d("Thread", "New Thread created diet")
                    var listDietClass:MutableList<DietDataClass> = mutableListOf()

                    var i=0
                    while (i<it.result!!.documents.size){

                        var DietDataSnapshot=it.result!!.documents[i]
                        listDietClass.add(DietDataClass(DietDataSnapshot.id,
                            DietDataSnapshot["Day"].toString(),
                            DietDataSnapshot["Name"].toString(),
                            DietDataSnapshot["Time"].toString()
                        ))
                    i++
                    }
                dietMethods.insert(listDietClass)
                    }else{
                        //TODO(Insert COMPLETABLE)
                    }
                }.start()
                }

            firebaseFirestore.collection("Users").document(firebaseAuth.uid.toString()).collection("DietDetails").get().
                    addOnCompleteListener {Thread{d("Thread2", "New Thread created2")
                        var listDietDetails:MutableList<DietList> = mutableListOf()
                        var i=0
                        while (i<it.result!!.documents.size){

                            var DietDataSnapshot=it.result!!.documents[i]
                            listDietDetails.add(
                                DietList(
                                    i,
                                    DietDataSnapshot.id,
                                DietDataSnapshot["Quantity"].toString(),
                                DietDataSnapshot["FoodItem"].toString(),
                                DietDataSnapshot["Carbohydrates"].toString(),
                                    DietDataSnapshot["Protein"].toString(),
                                    DietDataSnapshot["Fats"].toString()
                            ))
                            i++
                        }
                        dietMethods.insertList(listDietDetails)
                    }.start()
                    }

            }

    fun getDietData(day:String):Flowable<List<DietDataClass>>{
        d("Observable", "Observable called")
        return dietMethods.getdata(day).subscribeOn(Schedulers.io())
    }

    fun getDietList(diet_id:String):Flowable<List<DietList>>{
        d("Observable", "Observable called2")
    return dietMethods.getlist(diet_id).subscribeOn(Schedulers.io())
    }
}