package com.scwen.wanandroid.mine

import com.scwen.wanandroid.base.BasePresenter
import com.scwen.wanandroid.mine.model.LoginModel

/**
 * Created by scwen on 2019/5/27.
 *  QQ ：811733738
 *  作用：
 */
class LoginPresenter : BasePresenter<LoginModel, LoginView>() {

    override fun createModel(): LoginModel? = LoginModel()


}