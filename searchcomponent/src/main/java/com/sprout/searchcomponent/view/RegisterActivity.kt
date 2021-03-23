package com.sprout.searchcomponent.view

import android.widget.Toast
import com.sprout.baselibrary.base.BaseDataActivity
import com.sprout.baselibrary.bean.RegisterBean
import com.sprout.baselibrary.contract.IRegister
import com.sprout.searchcomponent.R
import com.sprout.searchcomponent.presenter.ImpRegisterPresenter
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.activity_login.password
import kotlinx.android.synthetic.main.activity_login.user_name
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Pattern

class RegisterActivity : BaseDataActivity<ImpRegisterPresenter>(),IRegister.IView{
    var kv = MMKV.defaultMMKV()

    override fun cityResult(bean: RegisterBean?) {
        if(bean!!.errno == 0){
            Toast.makeText(this,"註冊成功",Toast.LENGTH_SHORT).show()
            finish()
        }else{
            Toast.makeText(this,"註冊失敗"+bean.errmsg,Toast.LENGTH_SHORT).show()
        }
    }

    override fun getPresenter(): ImpRegisterPresenter = ImpRegisterPresenter()

    override fun initMain() {


       btn_register.setOnClickListener {
           var name = user_name.text.toString()
           var pass = password.text.toString()
           val all = "^[a-zA-Z0-9]+$"
           if (Pattern.matches(all, name) && Pattern.matches(all, pass)) {
               var map: HashMap<String, String> = hashMapOf(Pair("username", name),
                       Pair("password", pass), Pair("imei", "qewrtad112345zfs"), Pair("lng", "34.02235"), Pair("lat", "106.25643"))
               presenter.transfer(map)
           }else{
               Toast.makeText(this,"包含特殊字符",Toast.LENGTH_SHORT).show()

           }
       }
    }

    override fun getLayoutID(): Int = R.layout.activity_register
}