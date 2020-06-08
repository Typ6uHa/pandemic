package ru.aizat.diplom.ui.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_task.view.*
import ru.aizat.diplom.R

class TasksAdapter : ListAdapter<Value, TasksAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_task, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(value: Value) {
            itemView.tvId.text = "Задание #" +value.id.toString()
            itemView.tvTitle.text = value.description
            itemView.tvName.text = value.name
            itemView.tvPhone.text = value.phoneNumber
            itemView.tvPlace.text = value.adress
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Value>() {
            override fun areItemsTheSame(p0: Value, p1: Value): Boolean {
                return p0 == p1
            }

            override fun areContentsTheSame(p0: Value, p1: Value): Boolean {
                return true
            }
        }
    }
}