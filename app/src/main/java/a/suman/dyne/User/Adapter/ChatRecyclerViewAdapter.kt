package a.suman.dyne.User.Adapter

import a.suman.dyne.R
import a.suman.dyne.User.Fragments.Chat.Data.chatdataclass
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.SearchView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_chatlist.view.*

class ChatRecyclerViewAdapter(val onClickListener: onClickListener) : RecyclerView.Adapter<ChatViewHolder>(){

    var chatlist:List<chatdataclass> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
    return ChatViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.chat_recyclerview, parent, false))
    }

    override fun getItemCount(): Int {
        return chatlist.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.textView45.text=chatlist[position].trainern
        holder.cardView.setOnClickListener { onClickListener.onClick(chatlist[position].chatID, chatlist[position].trainern) }
    }

    fun setData(chatlist:List<chatdataclass>){
        this.chatlist=chatlist
        notifyDataSetChanged()
    }

}
interface onClickListener{
    fun onClick(chatid:String, trainerN:String)
}
class ChatViewHolder(view: View):RecyclerView.ViewHolder(view){
    val cardView=view.findViewById<CardView>(R.id.cardview)
    val textView45=view.findViewById<TextView>(R.id.textView45)
}