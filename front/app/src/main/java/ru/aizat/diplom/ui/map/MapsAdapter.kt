package ru.aizat.diplom.ui.map

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ru.aizat.diplom.ui.map.inner_fragment.MapCases
import ru.aizat.diplom.ui.map.inner_fragment.StaticCases
import java.lang.Exception

class MapsAdapter(val childFragmentManager: FragmentManager) : FragmentStatePagerAdapter(childFragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> MapCases.getInstance()
            1 -> StaticCases.getInstance()
            else -> throw Exception()
        }
    }

    override fun saveState(): Parcelable? {
        return null
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "На карте"
            1 -> "Статистика"
            else -> throw Exception()
        }
    }
}