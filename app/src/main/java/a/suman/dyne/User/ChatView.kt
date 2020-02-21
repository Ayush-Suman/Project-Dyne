package a.suman.dyne.User

import a.suman.dyne.Chats
import a.suman.dyne.R
import a.suman.dyne.User.Fragments.Chat.Data.chatmethods
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.room.FtsOptions
import co.intentservice.chatui.models.ChatMessage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_chat_view.*

class ChatView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_view)

        val bundle:Bundle?=intent.extras
        val chatID=bundle!!.getString("ChatID")
        textView.text=bundle!!.getString("TrainerN")
        val firebaseAuth=FirebaseAuth.getInstance()
        val firebaseFirestore=FirebaseFirestore.getInstance()
        firebaseFirestore.collection("Chat").document(chatID!!).collection("Chats").orderBy("time", Query.Direction.ASCENDING).addSnapshotListener {
                querySnapshot, firebaseFirestoreException ->
            chatview.clearMessages()
            var i=0
            d("fg",querySnapshot!!.documents.size.toString())


            while (i<querySnapshot!!.documents.size){
            if(querySnapshot!!.documents[i]["sentbyuser"].toString()=="true"){
                var chatMessage=ChatMessage(querySnapshot!!.documents[i]["chat"].toString(),
                    querySnapshot!!.documents[i]["time"].toString().toLong(),
                    ChatMessage.Type.SENT
                )
                runOnUiThread{
                    chatview.addMessage(chatMessage)}
            }else{
                var chatMessage=ChatMessage(querySnapshot!!.documents[i]["chat"].toString(),
                    querySnapshot!!.documents[i]["time"].toString().toLong(),
                    ChatMessage.Type.RECEIVED
                )
                runOnUiThread{
                    chatview.addMessage(chatMessage)}
            }

                i++
            }
            }
    chatview.setOnSentMessageListener {
        firebaseFirestore.collection("Chat").document(chatID).collection("Chats").document().set(
            Chats(it.message,it.timestamp, true) )

        chatview.inputEditText.text=null
        return@setOnSentMessageListener true
    }
        imageView21.setOnClickListener { onBackPressed() }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    finish()}
}
