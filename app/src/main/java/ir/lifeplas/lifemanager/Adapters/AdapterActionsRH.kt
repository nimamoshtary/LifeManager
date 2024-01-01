package ir.lifeplas.lifemanager.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.lifeplas.lifemanager.R
import ir.lifeplas.lifemanager.dataclass.ActionsItem

class AdapterActionsRH(private val data:ArrayList<ActionsItem>) : RecyclerView.Adapter<AdapterActionsRH.Recycler>() {
    inner class Recycler(Item :View) :RecyclerView.ViewHolder(Item){
        var txttitle = Item.findViewById<TextView>(R.id.txt_title)
        var txtsub = Item.findViewById<TextView>(R.id.txt_sub)
        fun bindData(position:Int){
            txttitle.text = data[position].textTitle.toString()
            txtsub.text = data[position].textsub.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Recycler {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_actionsh,parent,false)
        return Recycler(layout)
    }

    override fun onBindViewHolder(holder: Recycler, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
