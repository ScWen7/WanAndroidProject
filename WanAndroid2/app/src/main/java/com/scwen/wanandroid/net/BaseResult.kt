package com.scwen.wanandroid.net

/**
 * Created by scwen on 2019/5/22.
 *  QQ ：811733738
 *  作用：
 */
data class BaseResult<T>(
    var errorCode: Int = 0,
    var errorMsg: String = "",
    var data: T
)