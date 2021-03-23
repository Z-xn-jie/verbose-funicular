package com.sprout.searchcomponent.view

import android.content.Intent
import android.widget.Toast
import com.sprout.baselibrary.base.BaseDataActivity
import com.sprout.baselibrary.bean.LoginBean
import com.sprout.baselibrary.contract.ILogin
import com.sprout.searchcomponent.R
import com.sprout.searchcomponent.presenter.ImpLoginPresenter
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern

class LoginActivity : BaseDataActivity<ImpLoginPresenter>() ,ILogin.IView{
    var kv = MMKV.defaultMMKV()

    override fun getPresenter(): ImpLoginPresenter = ImpLoginPresenter()

    override fun initMain() {
        login.setOnClickListener {
            var name = user_name.text.toString()
            var pass = password.text.toString()
            val all = "^[a-zA-Z0-9]+$"
            if (Pattern.matches(all, name) && Pattern.matches(all, pass)) {
                var map:HashMap<String,String>  = hashMapOf(Pair("username",name),
                        Pair("password",pass))
                presenter.transfer(map)
            }else{
                Toast.makeText(this,"包含特殊字符",Toast.LENGTH_SHORT).show()

            }

        }

        register.setOnClickListener {
            startActivity(Intent(this@LoginActivity,RegisterActivity::class.java))
            user_name.text.clear()
            password.text.clear()
        }
    }

    override fun getLayoutID(): Int= R.layout.activity_login

    override fun cityResult(bean: LoginBean?) {
        if(bean!!.data.code ==200){
            Toast.makeText(this,"登錄成功，已存儲token", Toast.LENGTH_SHORT).show()
            kv?.encode("token",bean.data.token )
            finish()
        }else{
            Toast.makeText(this,bean.data.msg, Toast.LENGTH_SHORT).show()
        }
    }
}