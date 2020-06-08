package ru.aizat.diplom.data

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.aizat.diplom.utils.addLoggingWhenDebug
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

object ApiFactory {
    private val services: MutableMap<KClass<*>, Any> = HashMap()

    val gson: Gson by lazy {
        GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl("http://covid19.bvn13.com/stats/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttp)
                .build()
    }

    private val heroku: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://itiscovidapi.herokuapp.com/api/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp)
            .build()
    }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
                .addInterceptor {
                    val response = it.proceed(it.request())

                    if (response.code() in 200..299) {
                        return@addInterceptor response
                    }
                    response
                }
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
                .addLoggingWhenDebug()
                .build()
    }


    fun <T : Any> getRetrofitService(retrofitServiceClass: KClass<T>): T {
        @Suppress("UNCHECKED_CAST")
        var service = services[retrofitServiceClass] as T?

        if (service != null) {
            return service
        }

        service = retrofit.create(retrofitServiceClass.java)
        services[retrofitServiceClass] = service

        return service
    }

    fun <T : Any> getHerokuService(retrofitServiceClass: KClass<T>): T {
        @Suppress("UNCHECKED_CAST")
        var service = services[retrofitServiceClass] as T?

        if (service != null) {
            return service
        }

        service = heroku.create(retrofitServiceClass.java)
        services[retrofitServiceClass] = service

        return service
    }
}