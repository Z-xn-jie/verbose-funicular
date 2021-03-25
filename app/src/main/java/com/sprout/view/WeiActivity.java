package com.sprout.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sprout.R;
import com.sprout.baselibrary.base.BaseActivity;
import com.sprout.baselibrary.base.BaseDataActivity;
import com.sprout.baselibrary.bean.MoneyBean;
import com.sprout.baselibrary.contract.IMain;
import com.sprout.baselibrary.net.ApiService;
import com.sprout.baselibrary.net.INetCallBack;
import com.sprout.baselibrary.utils.RetrofitUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import butterknife.internal.Constants;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.sprout.baselibrary.net.UrlConstant.BASE_MONEY;
import static com.sprout.baselibrary.net.UrlConstant.MONEY_URL;

public class WeiActivity extends BaseActivity implements IMain.IView {


    @Override
    public void initMain() {
        Button button = findViewById(R.id.btn_ok);
        regToWx();
        initPay();
        HashMap<String,String> body = new HashMap<>();
        body.put("paytype ","1");

      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              RetrofitUtil instance = RetrofitUtil.getInstance();
              instance.getMoney();
              instance.postBody(MONEY_URL, body, new INetCallBack<MoneyBean>() {
                  @Override
                  public void success(MoneyBean moneyBean) {
                      PayReq req = new PayReq();
                      req.appId			="wx0dc7c1fa36761c64";
                      req.partnerId		="1607146819";
                      req.prepayId		=moneyBean.getData().getPrepay_id();
                      req.nonceStr		=moneyBean.getData().getNoncestr();
                      req.timeStamp		=moneyBean.getData().getTimestamp();
                      req.packageValue	="sign=WXPay";
                      req.sign			=moneyBean.getData().getSign();
                      api.sendReq(req);
                  }

                  @Override
                  public void onFail(String error) {

                  }
              });
          }
      });

    }

    @Override
    public int getLayoutId() {
        return  R.layout.activity_wei;
    }
    private void initPay() {

    }
    @Override
    public void cityResult(MoneyBean bean) {
        MoneyBean.DataDTO data = bean.getData();

    }
    // APP_ID 替换为你的应用从官方网站申请到的合法appID
    private static final String APP_ID = "wx0dc7c1fa36761c64";

    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;

    private void regToWx() {
            // 通过WXAPIFactory工厂，获取IWXAPI的实例
            api = WXAPIFactory.createWXAPI(this, APP_ID, true);

            // 将应用的appId注册到微信
            api.registerApp(APP_ID);

            //建议动态监听微信启动广播进行注册到微信
            registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    // 将该app注册到微信
                    api.registerApp(APP_ID);
                }
            }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));

    }



}