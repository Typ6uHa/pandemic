package ru.aizat.diplom.ui.map.inner_fragment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import ru.aizat.diplom.R
import ru.aizat.diplom.data.HerokyRepository
import ru.aizat.diplom.utils.addSchedulers


class MapCases : Fragment() {

    private lateinit var mMap: GoogleMap
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val repository = HerokyRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync { googleMap ->
            mMap = googleMap
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

            (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment)

            compositeDisposable += repository.getCases().addSchedulers()
                .subscribe({
                    for (item in it.value) {
                        mMap.addMarker(
                            MarkerOptions().position(
                                LatLng(
                                    item.latitude,
                                    item.longitude
                                )
                            ).icon(BitmapDescriptorFactory.fromResource(R.drawable.place)))
                    }
                }, {})
        }
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
        vectorDrawable!!.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
        val bitmap =
            Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    companion object {
        fun getInstance(): MapCases {
            return MapCases()
        }
    }
}