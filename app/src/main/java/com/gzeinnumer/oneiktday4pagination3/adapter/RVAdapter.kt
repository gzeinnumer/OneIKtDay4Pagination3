package com.gzeinnumer.oneiktday4pagination3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gzeinnumer.oneiktday4pagination3.R
import com.gzeinnumer.oneiktday4pagination3.model.ResultsItem
import org.w3c.dom.CharacterData

//todo 8
class RVAdapter : PagingDataAdapter<ResultsItem, RVAdapter.MyViewHolder>(DiffUtilCallBack()){

    //todo 9
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent, false)
        return MyViewHolder(inflater)
    }

    //todo 10
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    //todo 11
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        val img: ImageView = view.findViewById(R.id.img)
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvDesc: TextView = view.findViewById(R.id.tv_desc)

        fun bind(item: ResultsItem?) {
            item?.let {
                tvName.text = it.name
                tvDesc.text = it.species
                Glide.with(img)
                    .load(it.image)
                    .centerCrop()
                    .into(img)
            }
        }
    }

    //todo 12
    class DiffUtilCallBack: DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.species == newItem.species
        }

    }
}