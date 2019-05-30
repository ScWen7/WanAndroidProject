package com.scwen.wanandroid.net

import com.scwen.wanandroid.constant.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by scwen on 2019/5/22.
 *  QQ ：811733738
 *  作用：
 */
class HttpClient private constructor() {
    companion object {
        private val instance by lazy { HttpClient() }
        val api by lazy { instance.buildApi() }
    }

    private fun buildApi() = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(buildOkHttp())
        .build()
        .create(Api::class.java)

    private fun buildOkHttp(): OkHttpClient {
        val sslParams1 = HttpsUtils.getSslSocketFactory()
        var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .cookieJar(CookieJarImpl(PersistentCookieStore()))
            .sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager)
            .hostnameVerifier(HttpsUtils.SafeHostnameVerifier())
            .build()
        okHttpClient.dispatcher().maxRequestsPerHost = 8
        return okHttpClient
    }

}