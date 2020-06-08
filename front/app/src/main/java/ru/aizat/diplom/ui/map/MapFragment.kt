package ru.aizat.diplom.ui.map

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.item_new_case.view.*
import ru.aizat.diplom.R

class MapFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabMap.setOnClickListener { view ->
            val builder = AlertDialog.Builder(requireContext())
            val customShareDialog = layoutInflater.inflate(R.layout.item_new_case, null)
            builder.setView(customShareDialog)
            val dialog = builder.create()
            dialog.show()
            customShareDialog.newCaseFrag_cancel_imButton.setOnClickListener {
                dialog.dismiss()
            }
        }

        progressContainer.visibility = View.VISIBLE
        viewPager.visibility = View.GONE

        val handler = Handler()
        handler.postDelayed(Runnable {
            progressContainer.visibility = View.GONE
            viewPager.visibility = View.VISIBLE
        }, 2000)

        viewPager.offscreenPageLimit = 2
        viewPager.adapter = MapsAdapter(childFragmentManager)
        tabs.setupWithViewPager(viewPager)
    }
}