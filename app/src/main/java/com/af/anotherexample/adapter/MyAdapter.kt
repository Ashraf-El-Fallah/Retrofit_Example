package com.af.anotherexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.af.anotherexample.model.Post
import com.af.retrofit.databinding.ItemTodoBinding
import com.af.retrofit.databinding.RowLayoutBinding

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder>() {

    private var myList = emptyList<Post>()

    inner class MyAdapterViewHolder(private val binding: RowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(myPost: Post) {
            binding.id.text = myPost.id.toString()
            binding.userId.text = myPost.userId.toString()
            binding.body.text = myPost.body
            binding.title.text = myPost.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapterViewHolder {
        val view = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyAdapterViewHolder, position: Int) {
        val currentPosition = myList[position]
        holder.bind(currentPosition)
    }

    fun setData(newList: List<Post>) {
        myList = newList
        notifyDataSetChanged()
    }
}