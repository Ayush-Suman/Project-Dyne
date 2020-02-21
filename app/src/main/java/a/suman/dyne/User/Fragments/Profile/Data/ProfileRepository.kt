package a.suman.dyne.User.Fragments.Profile.Data

import a.suman.dyne.User.DatabaseDyne
import android.content.Context
import android.util.Log.d
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ProfileRepository(context: Context) {

    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
    val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    val profileMethods=DatabaseDyne.getInstance(context).getProfileMethods()

    fun updateData(profileDataClass: ProfileDataClass){
        firebaseFirestore.collection("Users").document(firebaseAuth.uid.toString()).set(profileDataClass)
        firebaseFirestore.collection("Users").document(firebaseAuth.uid.toString()).addSnapshotListener{
                querySnapshot, firebaseFirestoreException ->
            fetchProfileData()
        }
    }


    fun fetchProfileData(){
        firebaseFirestore.collection("Users").document(firebaseAuth.uid.toString()).get().addOnCompleteListener{
            Thread {
                if (it.isSuccessful) {
                    d("Thread", "New Thread created Profile")
                    var profileDataSnapShot = it.result!!
                    val profileDataClass = ProfileDataClass(
                        profileDataSnapShot["email"].toString(),
                        profileDataSnapShot["name"].toString(),
                        profileDataSnapShot["mobile2"].toString(),
                        profileDataSnapShot["gender"].toString(),
                        profileDataSnapShot["weight"].toString(),
                        profileDataSnapShot["height"].toString()
                    )
                    profileMethods.insert(profileDataClass)}
                else{
                    //TODO(Insert COMPLETABLE)
                }
                }.start()
        }
    }


    fun getProfileData(): Flowable<ProfileDataClass> {
        d("Observable", "Observable called")
        return profileMethods.getdata().subscribeOn(Schedulers.io())
    }



}