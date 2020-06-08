package ru.aizat.diplom.ui.tasks

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_tasks.*
import kotlinx.android.synthetic.main.item_new_task.*
import kotlinx.android.synthetic.main.item_new_task.view.*
import ru.aizat.diplom.R

class TasksFragment : Fragment() {

    private val adapter: TasksAdapter = TasksAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressContainer.visibility = VISIBLE
        recyclerView.visibility = GONE

        val handler = Handler()
        handler.postDelayed(Runnable {
            progressContainer.visibility = GONE
            recyclerView.visibility = VISIBLE
        }, 3000)

        fabTasks.setOnClickListener { view ->
            val builder = AlertDialog.Builder(requireContext())
            val customShareDialog = layoutInflater.inflate(R.layout.item_new_task, null)
            builder.setView(customShareDialog)
            val dialog = builder.create()


            customShareDialog.btnNewTask.setOnClickListener {
                val value = Value(
                    adress = customShareDialog.newTaskFrag_address_et.text.toString(),
                    cityId = 1,
                    description = customShareDialog.newTaskFrag_description_et.text.toString(),
                    id = 1349,
                    latitude = 12.34,
                    longitude = 12.43,
                    phoneNumber = customShareDialog.newTaskFrag_phoneNumber_et.text.toString(),
                    phoneSerialNumber = "1",
                    status = 1,
                    name = customShareDialog.newTaskFrag_name_et.text.toString()

                )
                adapter.submitList(returnTaskList(value))
                dialog.dismiss()
                recyclerView.scrollToPosition(0)

            }

            dialog.show()
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        adapter.submitList(returnTaskList(null))
    }

    private fun returnTaskList(value: Value?): List<Value> {
        return if (value != null) {
            var tasks = listOf<Value>(value)
            tasks += generateTasks()
            tasks
        } else {
            generateTasks()
        }
    }

    private fun generateTasks(): List<Value> {
        return listOf(
            Value(
                adress = "г.Казань, ул. Пушкина 47 кв.270",
                cityId = 1,
                description = "Купить продукты",
                id = 1345,
                latitude = 12.34,
                longitude = 12.43,
                phoneNumber = "+79358354876",
                phoneSerialNumber = "1",
                status = 1,
                name = "Тимофеев Валерий"
            ),
            Value(
                adress = "г.Казань, ул. Калинина 22 кв.2",
                cityId = 1,
                description = "Вынести мусор",
                id = 1258,
                latitude = 12.34,
                longitude = 12.43,
                phoneNumber = "+79276486870",
                phoneSerialNumber = "1",
                status = 1,
                name = "Гаврилова Юлия"
            ),
            Value(
                adress = "г.Казань, ул. Достоевского 11 кв.170",
                cityId = 1,
                description = "Выгулять собаку",
                id = 2475,
                latitude = 12.34,
                longitude = 12.43,
                phoneNumber = "+79514625733",
                phoneSerialNumber = "1",
                status = 1,
                name = "Спицина Евгения"
            ),
            Value(
                adress = "г.Казань, ул. Аделя Кутуя 16 кв.50",
                cityId = 1,
                description = "Купить продукты",
                id = 3245,
                latitude = 12.34,
                longitude = 12.43,
                phoneNumber = "+79516158224",
                phoneSerialNumber = "1",
                status = 1,
                name = "Колесников Николай"
            ),
            Value(
                adress = "г.Казань, ул.Щапова 46 кв.13",
                cityId = 1,
                description = "Купить лекарства",
                id = 2931,
                latitude = 12.34,
                longitude = 12.43,
                phoneNumber = "+79221193570",
                phoneSerialNumber = "1",
                status = 1,
                name = "Сулайманова Алина"
            ),
            Value(
                adress = "г.Казань, ул.Адоратского 39 кв.256",
                cityId = 1,
                description = "Купить продукты",
                id = 3007,
                latitude = 12.34,
                longitude = 12.43,
                phoneNumber = "+79585469557",
                phoneSerialNumber = "1",
                status = 1,
                name = "Нафиков Булат"
            ),
            Value(
                adress = "г.Казань, ул. Вишневского 15 кв.297",
                cityId = 1,
                description = "Выгулять собак",
                id = 2566,
                latitude = 12.34,
                longitude = 12.43,
                phoneNumber = "+792756682541",
                phoneSerialNumber = "1",
                status = 1,
                name = "Валеева Карина"
            ),
            Value(
                adress = "г.Казань, ул.Маяковского 12 кв.49",
                cityId = 1,
                description = "Купить лекарства",
                id = 1058,
                latitude = 12.34,
                longitude = 12.43,
                phoneNumber = "+79568665235",
                phoneSerialNumber = "1",
                status = 1,
                name = "Бочкарев Антон"
            ),
            Value(
                adress = "г.Казань, ул.Карбышева 89 кв.69",
                cityId = 1,
                description = "Вынести мусор",
                id = 1246,
                latitude = 12.34,
                longitude = 12.43,
                phoneNumber = "+79552685563",
                phoneSerialNumber = "1",
                status = 1,
                name = "Замалиев Руслан"
            ),
            Value(
                adress = "г.Казань, ул. Вишневского 15 кв.297",
                cityId = 1,
                description = "Сходить в магазин",
                id = 1248,
                latitude = 12.34,
                longitude = 12.43,
                phoneNumber = "+79248513151",
                phoneSerialNumber = "1",
                status = 1,
                name = "Антохина Алеся"
            )
        )
    }
}