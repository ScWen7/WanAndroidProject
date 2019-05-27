package com.scwen.wanandroid.mine

import android.content.Context
import android.content.Intent
import android.net.nsd.NsdManager
import android.provider.SyncStateContract
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.gyf.immersionbar.ImmersionBar
import com.scwen.wanandroid.R
import com.scwen.wanandroid.base.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by scwen on 2019/5/27.
 *  QQ ：811733738
 *  作用：
 */
class LoginActivity : BaseMvpActivity<LoginView, LoginPresenter>() {

    companion object {
        fun start(context: Context) {
            var intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun createPresenter(): LoginPresenter = LoginPresenter()

    override fun getLayoutRes(): Int = R.layout.activity_login


    override fun initActivity() {
        super.initActivity()
        initImmBar()
    }

    override fun initImmBar() {
        mImmersionBar = ImmersionBar.with(this)
        mImmersionBar?.let {
            it.statusBarDarkFont(true, 0.4f)
            it.init()
        }
    }

    val LOGIN_TYPE = 1
    val REGIST_TYPE = 2

    var state = LOGIN_TYPE

    override fun initData() {
        iv_close_login.setOnClickListener { finish() }

        ll_login.setOnClickListener {
            state = LOGIN_TYPE
            changePage()
            changeTabState(tv_login, v_login)
        }

        ll_regist.setOnClickListener {
            state = REGIST_TYPE
            changePage()
            changeTabState(tv_regist, v_regist)
        }
        checkBtn()

    }

    fun changePage() {
        if (state == LOGIN_TYPE) {
            til_re_pwd.visibility = View.GONE
        } else {
            til_re_pwd.visibility = View.VISIBLE
        }
    }

    /**
     * 改变tab切换
     */
    private fun changeTabState(tv: TextView, v: View) {
        tv_login.setTextColor(ContextCompat.getColor(applicationContext, R.color.FF8E92AD))
        tv_login.textSize = 16f
        v_login.visibility = View.GONE

        tv_regist.setTextColor(ContextCompat.getColor(applicationContext, R.color.FF8E92AD))
        tv_regist.textSize = 16f
        v_regist.visibility = View.GONE

        tv.setTextColor(ContextCompat.getColor(applicationContext, R.color.FFFFBF00))
        tv.textSize = 20f
        v.visibility = View.VISIBLE

    }


    private fun changeBtnState(available: Boolean) {
        if (available) {
            iv_login_next.setImageResource(R.mipmap.ic_sign_disable_glyph)
            iv_login_next.setClickable(false)
            iv_login_next.setEnabled(false)
        } else {
            iv_login_next.setImageResource(R.mipmap.ic_sign_glyph)
            iv_login_next.isClickable = true
            iv_login_next.isEnabled = true
        }
    }

    private fun checkBtnAvailable() {
        til_mobile.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                checkBtn()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                til_mobile.error = ""// 必须加上这个，否则会导致内容删除后，error信息显示为空白
                til_mobile.isErrorEnabled = false
            }
        })
        til_pwd.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                checkBtn()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                til_pwd.error = ""// 必须加上这个，否则会导致内容删除后，error信息显示为空白
                til_pwd.isErrorEnabled = false
            }
        })
        til_re_pwd.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                checkBtn()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                til_re_pwd.error = ""// 必须加上这个，否则会导致内容删除后，error信息显示为空白
                til_re_pwd.isErrorEnabled = false
            }
        })

    }

    private fun checkBtn() {
        if (state == REGIST_TYPE) {
            changeBtnState(
                TextUtils.isEmpty(til_mobile.editText?.text.toString().trim())
                        || TextUtils.isEmpty(
                    til_pwd.editText?.text.toString().trim()
                )
                        || TextUtils.isEmpty(
                    til_re_pwd.editText?.text.toString().trim()
                )
            )
        } else {
            changeBtnState(
                TextUtils.isEmpty(til_mobile.editText?.text.toString().trim()) || TextUtils.isEmpty(
                    til_pwd.editText?.text.toString().trim()
                )
            )
        }
    }
}