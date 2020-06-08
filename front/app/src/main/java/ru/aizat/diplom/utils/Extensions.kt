package ru.aizat.diplom.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ru.aizat.diplom.BuildConfig


fun <T> Observable<T>.addSchedulers(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.addSchedulers(): Single<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Flowable<T>.addSchedulers(): Flowable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Completable.addSchedulers(): Completable {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Maybe<T>.addSchedulers(): Maybe<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun OkHttpClient.Builder.addLoggingWhenDebug(): OkHttpClient.Builder {
    if (BuildConfig.DEBUG) {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return addInterceptor(interceptor)
    }
    return this
}

fun pixelToDp(dp: Int, context: Context): Int {
    return (dp * (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT))
}