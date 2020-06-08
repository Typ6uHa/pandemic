package ru.aizat.diplom.ui.map.inner_fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_statistic.view.*
import ru.aizat.diplom.R
import ru.aizat.diplom.ui.tasks.Value

class StatAdapter : ListAdapter<StatUiModel, StatAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_statistic, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(statUiModel: StatUiModel) {
            itemView.frag_statistics_city_tv.text = statUiModel.region.toString()
            itemView.frag_statistics_count_tv.text = statUiModel.count.toString()
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<StatUiModel>() {
            override fun areItemsTheSame(p0: StatUiModel, p1: StatUiModel): Boolean {
                return p0.javaClass == p1.javaClass
            }

            override fun areContentsTheSame(p0: StatUiModel, p1: StatUiModel): Boolean {
                return p0 == p1
            }
        }
    }
}