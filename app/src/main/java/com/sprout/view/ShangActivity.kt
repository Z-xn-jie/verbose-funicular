package com.sprout.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.sdk.android.oss.*
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback
import com.alibaba.sdk.android.oss.common.OSSLog
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask
import com.alibaba.sdk.android.oss.model.PutObjectRequest
import com.alibaba.sdk.android.oss.model.PutObjectResult
import com.sprout.R
import kotlinx.android.synthetic.main.activity_shang.*


class ShangActivity : AppCompatActivity() {
    lateinit var oss: OSS
    lateinit var endpoint:String
    lateinit var accessKeyId:String
    lateinit var accessKeySecret:String
    lateinit var uri:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shang)
        OSSLog.enableLog()
         endpoint = "http://oss-cn-beijing.aliyuncs.com"
         accessKeyId = "LTAI5tHerrjErGjmgwmtTdtD"
         accessKeySecret = "m79XPSGb9PQZx67WbVeKVPqR4CJJRv"
        uri = "/storage/emulated/0/tencent/qq_images/null1e1a005ca1f8eb36.jpg"
        val stsServer = "http://abc.com:8080"
// 推荐使用OSSAuthCredentialsProvider。token过期可以及时更新。
        val credentialProvider: OSSCredentialProvider = OSSStsTokenCredentialProvider(accessKeyId, accessKeySecret, "")

// 配置类如果不设置，会有默认配置。
        val conf = ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒。
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒。
        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个。
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次。

        oss = OSSClient(getApplicationContext(), endpoint,credentialProvider,conf)
        btn_ok.setOnClickListener {
            initView()
        }

    }

    private fun initView() {
// 构造上传请求。

        // 构造上传请求。
        val put = PutObjectRequest("demoms", "key=image/2014/1.jpg", uri)

// 异步上传时可以设置进度回调。

// 异步上传时可以设置进度回调。
        put.progressCallback = OSSProgressCallback { request, currentSize, totalSize -> Log.d("PutObject", "currentSize: $currentSize totalSize: $totalSize") }

        val task: OSSAsyncTask<*> = oss.asyncPutObject(put, object : OSSCompletedCallback<PutObjectRequest?, PutObjectResult> {
            override fun onSuccess(request: PutObjectRequest?, result: PutObjectResult) {
                Log.d("PutObject", "UploadSuccess")
                Log.d("ETag", result.eTag)
                Log.d("RequestId", result.requestId)
            }

            override fun onFailure(request: PutObjectRequest?, clientExcepion: ClientException?, serviceException: ServiceException?) {
                // 请求异常。
                if (clientExcepion != null) {
                    // 本地异常，如网络异常等。
                    clientExcepion.printStackTrace()
                }
                if (serviceException != null) {
                    // 服务异常。
                    Log.e("ErrorCode", serviceException.errorCode)
                    Log.e("RequestId", serviceException.requestId)
                    Log.e("HostId", serviceException.hostId)
                    Log.e("RawMessage", serviceException.rawMessage)
                }
            }
        })
// task.cancel(); // 可以取消任务。
// task.waitUntilFinished(); // 等待任务完成。

    }
}