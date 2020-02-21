package a.suman.dyne.User.Fragments.Chat.Data

import a.suman.dyne.User.DatabaseDyne
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class chatrepository(context: Context){
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
    val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val chatMethods = DatabaseDyne.getInstance(context).getchatmethods()

    fun fetchData(){
        firebaseFirestore.collection("Users").document(firebaseAuth.currentUser!!.uid).collection("Chats").get().
            addOnCompleteListener {Thread{
                if(it.isSuccessful){
                var i=0
                var chatlist:MutableList<chatdataclass> = mutableListOf()
                while(i<it.result!!.documents.size){
                var chats=it.result!!.documents
                    var name:String?=null
                        var chatdataclass=chatdataclass(
                        chats[i]["chatid"].toString(),
                        chats[i]["trainerid"].toString(),
                        chats[i]["tname"].toString())
                        chatlist.add(chatdataclass)
                        i++
                }
                chatMethods.insert(chatlist)}}.start()
            }
    }

    fun getData():Flowable<List<chatdataclass>>{
        return chatMethods.getData().subscribeOn(Schedulers.io())
    }
}