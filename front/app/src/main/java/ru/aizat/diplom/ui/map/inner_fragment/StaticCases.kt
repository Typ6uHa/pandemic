package ru.aizat.diplom.ui.map.inner_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.fragment_stats.*
import ru.aizat.diplom.R
import ru.aizat.diplom.data.Repository
import ru.aizat.diplom.data.StatsModel
import ru.aizat.diplom.utils.addSchedulers

class StaticCases : Fragment() {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val repository = Repository()
    val adapter = StatAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        radio.check(R.id.rboff)
        val flag = radio.checkedRadioButtonId == R.id.rboff
        compositeDisposable += repository.getStats(flag)
            .addSchedulers()
            .subscribe({
            adapter.submitList(it.toUiModel())
        }, {})

        radio.setOnCheckedChangeListener { group, checkedId ->
            val innerFlag = checkedId == R.id.rboff
            compositeDisposable += repository.getStats(innerFlag)
                .addSchedulers()
                .subscribe({
                    adapter.submitList(it.toUiModel())
                }, {})
        }
    }

    companion object {
        fun getInstance(): StaticCases {
            return StaticCases()
        }
    }
}

private fun StatsModel.toUiModel(): MutableList<StatUiModel> {
    val localList: MutableList<StatUiModel> = mutableListOf()

    for (item in stats) {
        localList.add(StatUiModel(item.died + item.healed + item.sick, item.region))
    }
    return localList
}
