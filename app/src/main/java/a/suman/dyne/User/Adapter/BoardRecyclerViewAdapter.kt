package a.suman.dyne.User.Adapter

import a.suman.dyne.R
import a.suman.dyne.User.Board
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class BoardRecyclerViewAdapter :RecyclerView.Adapter<BoardViewHolder>(){

     var listBoard:List<Board> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        return BoardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_advisory, parent, false))
    }

    override fun getItemCount(): Int {
        return listBoard.size
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.textView56.text=listBoard[position].name
        holder.textView57.text=listBoard[position].desc
    }

    fun setData(listBoard:List<Board>){
        this.listBoard=listBoard
        notifyDataSetChanged()
    }
}


class BoardViewHolder(view:View):RecyclerView.ViewHolder(view){
    val textView56=view.findViewById<TextView>(R.id.textView56)
    val textView57=view.findViewById<TextView>(R.id.textView57)
}