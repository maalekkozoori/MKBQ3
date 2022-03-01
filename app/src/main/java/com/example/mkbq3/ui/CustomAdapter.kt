package com.example.mkbq3.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.mkbq3.R
import com.example.mkbq3.data.User
import org.w3c.dom.Text

class CustomAdapter(private val context: Context,private val list: List<User>?,private val listner : OnItemClickListener):RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{

        var tvName :TextView = itemView.findViewById(R.id.tvFirstName)
        var tvUserId :TextView = itemView.findViewById(R.id.tvUserId)
        var tvLastName :TextView = itemView.findViewById(R.id.tvLastname)
        var tvNationalCode :TextView? = itemView.findViewById(R.id.tvNationalCode)


        init {

            itemView.setOnClickListener(this)
            itemView.setOnClickListener {
                Toast.makeText(context,  list?.get(adapterPosition)?._id, Toast.LENGTH_SHORT).show()


            }
        }

        fun bind(position: Int){
            tvName.text = list?.get(position)?.firstName
            tvLastName.text = list?.get(position)?.lastName
            tvNationalCode?.text = list?.get(position)?.nationalCode
            tvUserId.text = list?.get(position)?._id
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listner.onItemClick(position)
            }
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.user_list_item, parent, false)
        return ViewHolder(itemView)    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(position)    }

    override fun getItemCount(): Int = list!!.size


    interface OnItemClickListener{
        fun onItemClick(position : Int)
    }


}