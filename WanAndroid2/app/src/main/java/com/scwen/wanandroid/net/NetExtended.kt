package com.scwen.wanandroid.net

import com.bumptech.glide.load.HttpException
import com.google.gson.JsonParseException
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.*
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * Created by scwen on 2019/5/22.
 *  QQ ：811733738
 *  作用：
 */
private val onErrorStub: (String, Boolean) -> Unit = { s: String, b: Boolean -> }

fun <T : Any> Flowable<BaseResult<T>>.excute(
    onSuccess: (T) -> Unit,
    onHandleError: (String, Boolean) -> Unit = onErrorStub
): Disposable {
    return compose(io_main()).subscribeBy(
        onNext = {
            when (it.errorCode) {
                ErrorStatus.SUCCESS -> onSuccess.invoke(it.data)
                ErrorStatus.TOKEN_INVALID -> onHandleError.invoke(it.errorMsg, true)
                else -> onHandleError.invoke(it.errorMsg, false)
            }
        },
        onError = {
            onHandleError.invoke(handleException(it), false)
        }
    )
}


fun handleException(e: Throwable): String {
    e.printStackTrace()
    var errorMsg = "请求失败，请稍后重试"
    if (e is SocketTimeoutException
        || e is ConnectException
        || e is HttpException
    ) { //均视为网络错误
        errorMsg = "网络连接异常"
    } else if (e is JsonParseException
        || e is JSONException
        || e is ParseException
    ) {   //均视为解析错误
        errorMsg = "数据解析异常"
    } else if (e is UnknownHostException) {
        errorMsg = "网络连接异常"
    } else if (e is IllegalArgumentException) {
        errorMsg = "参数错误"
    } else {//未知错误
        errorMsg = "未知错误，可能抛锚了吧~"
    }
    return errorMsg
}


fun <T> io_main(): FlowableTransformer<T, T> {
    return FlowableTransformer {
        it.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}
