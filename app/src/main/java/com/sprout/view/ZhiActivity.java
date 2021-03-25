package com.sprout.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.sprout.R;

import java.util.Map;


public class ZhiActivity extends AppCompatActivity {
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "2021000117626766";

    /**
     * 用于支付宝账户登录授权业务的入参 pid。
     */
    public static final String PID = "2088621955442655";

    /**
     * 用于支付宝账户登录授权业务的入参 target_id。
     */
    public static final String TARGET_ID = "uoilvm2981@sandbox.com";

    /**
     * pkcs8 格式的商户私钥。
     * <p>
     * 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个，如果两个都设置了，本 Demo 将优先
     * 使用 RSA2_PRIVATE。RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议商户使用
     * RSA2_PRIVATE。
     * <p>
     * 建议使用支付宝提供的公私钥生成工具生成和获取 RSA2_PRIVATE。
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCjtUT0rJsE3kmD/GKqf0nzxgwj9EC/z5Pdm3+/2nb4RItPivV1lvUeKmi9sBIWf/vmPgzBVE400EMfe9ZqgVgBoTOOL9EwaWfygO4D/bP1txHB7TtN/4dcIm24k18hue2nlgp1i1aseqP4TpvDufb2Aq4bfJxM6hl6gjHBDDKJAuPWH8DZMgS+sxPbQT3IBzmMs9nyzLWktWTScB8Ip/YlavqM6qrrzWj08rNNQh8k5WgFhYMwy4mznkPKHihPMIMCdTGQ7VafywBdkfDyHcuXIL3g6lFxWITCpShHXUqmu8j3mLl73zCpysABODC0aTi8SFSWwAMDBYbjfwgR1lszAgMBAAECggEAIwLhfHJttNzVXCTd9RjD+B6i+g2Cy4LZXMukxqMyTmZaZOiTFlLymUD4pZjCRY2ZkeQzViVxlFn2AQW7FYwUBkUzgXU4h+IDGRn5zWC03BgK9C6IQGYlzM6QKKlb7xqUeQLducXTA5A3ASPOrE1ZQ8PO+JHt24U1bb3qZh71r9dVkSvrXaE6oDTmWBK4r3GcKrpowtvCypw1akIMbu1TJF87ImtDWTbCr1R6QNXT8hMnTjIwEZOA8SePpkeyM7PUqRyIr3lqbD05CjibLEeeVyAriU5iGZddoCI4VW+u32qJbLYywhH4J9bZrZ1ibpCBoYJkFipxrO1CdCSBYt5oAQKBgQDyamWjKb9vJZjfFpmf+b8QpcpOTBB5svFmWzumYzs7tf71DmHRP0WDQrdDWBHQ1Spai1dEf+LjiNy+FoRj2ZVavi3+DDEVOkxzS1w3UwFCQD9HYso8o+zcxW4GSyMDVzFkSJaKuXNYWJvNX/dvXrvmpaDK2mT9JhZqYLpmrNdL4wKBgQCs4cOH4qa+fC31aG8zZE3K3lE53+5lU2QjwQE1HxwIboB8sm+czom5v9Yl7LZbBoqpgeXNO/LU1wVOghQPWzHMj1yAcymp6gfOZXesFuC536zPvodTMhHOuEAJlveR2QM31sJ8DSXQP2AEFSRoRrghP36Fx8eXjgyjf7f2l9l0cQKBgCkdCG3ax7/nBX1YjOeH6PK+24fZ+LVbNvvgH0bB76rZ3ArdzJM7guOXLhO63RKpBIzkswFIG0fxz1Zh5UQ2A9GnDY/Xq39pKswnDNXJOx5sXQ8TXy9XsfVjepyQo7iOUi8q7HVMrJZgONRlIUb/LykYECQ3VAdnLUgPba26RK13AoGBAKRK9K/JNB8oEeD7O1pCgy88sUJnzb1bT3ou6//YraJRqSdXx3vkR7xPU6q81tuQTuwhtwqgYvfj03Y1ZezmBGBbCVREnR6Nepepk93l6/CYYTkX3rCwj329WZYDcwiWjFlfbXnIYNwTriKTGQND2a43Efz+y236n4YtVN0yl4PxAoGAS6CYpbzmxUN+6i/Wxgpbu4VBcXedlqf3W+oj6Qy0QRStS+TJ2jEdfINYr4PZa3C1rMcpBcJJ74sUeUbwFNUhy9vi6+vfqidp4wsXgaV7P0+NWRXt3p9ONSZg6xqTq51Eddg/42M4EzgwSiT6vWIPuoEfZYkC1Bz7GmsQxNvTCBg=";
    public static final String RSA_PRIVATE = "";
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        showAlert(ZhiActivity.this, getString(R.string.pay_success) + payResult);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        showAlert(ZhiActivity.this, getString(R.string.pay_failed) + payResult);
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        showAlert(ZhiActivity.this, getString(R.string.auth_success) + authResult);
                    } else {
                        // 其他状态值则为授权失败
                        showAlert(ZhiActivity.this, getString(R.string.auth_failed) + authResult);
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi);
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);

        //实例化客户端
        AlipayClient alipayClient = new
                DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APPID, RSA2_PRIVATE,
                "json", "utf_8", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnnnFpBK3VydK1dlHm8bWc3zLd2vPSmGpZ3W76rgEwhjPenwJ8PHX5nKsVYxmO1i+D+MWsOAZfcO+guGeIrwfho5uAHF+vxRu3Sl3oC+1EYzkkevTzk2ixTzPyjlc8lS6e3dQivydIRwlY+bYoTBJWlhbMzSzwReDDjwaq4hsJLFnr519czuTwUZyFwbdcJSWfyrCxIgM1KMfYBy1prelqntvFUpXvw/LFmYWMtOK5MnBrMzEML3KegkHY6sFwqu7TI/IB7jh2hCqwVF3gQ2xokYG1+qH6FyTMje7Pk5rdbPKxA6Ibe5TOhV2JV1ImSxEOMCxSSmsm+GBSFrTwLnI+wIDAQAB", "RSA2");
    }
    public void payV2(View v) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            showAlert(this, getString(R.string.error_missing_appid_rsa_private));
            return;
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo 的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(ZhiActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
    private static void showAlert(Context ctx, String info) {
        showAlert(ctx, info, null);
    }

    private static void showAlert(Context ctx, String info, DialogInterface.OnDismissListener onDismiss) {
        new AlertDialog.Builder(ctx)
                .setMessage(info)
                .setPositiveButton(R.string.confirm, null)
                .setOnDismissListener(onDismiss)
                .show();
    }

}