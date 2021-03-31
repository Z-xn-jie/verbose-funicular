package com.sprout.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import com.google.gson.Gson
import com.sprout.R
import com.sprout.baselibrary.bean.CimmitsBean
import com.sprout.baselibrary.net.INetCallBack
import com.sprout.baselibrary.utils.RetrofitUtil
import com.sprout.bean.CommitBean
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.activity_shang.*
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.File
import java.util.*
import kotlin.concurrent.timerTask


class ShangActivity : AppCompatActivity() {
    lateinit var oss: OSS
    lateinit var endpoint: String
    lateinit var accessKeyId: String
    lateinit var accessKeySecret: String
    lateinit var commitBean:CommitBean

    var downLoad: Boolean = true
    var time: Int = 100000
    var count: Int = 0
    lateinit var res:List<CommitBean.Re>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shang)
        val defaultMMKV = MMKV.defaultMMKV()
        OSSLog.enableLog()
        endpoint = "http://oss-cn-beijing.aliyuncs.com"
        accessKeyId = "LTAI5tHerrjErGjmgwmtTdtD"
        accessKeySecret = "m79XPSGb9PQZx67WbVeKVPqR4CJJRv"
        val stsServer = "http://abc.com:8080"
// 推荐使用OSSAuthCredentialsProvider。token过期可以及时更新。
        val credentialProvider: OSSCredentialProvider = OSSStsTokenCredentialProvider(accessKeyId, accessKeySecret, "")

// 配置类如果不设置，会有默认配置。
        val conf = ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒。
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒。
        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个。
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次。

        oss = OSSClient(getApplicationContext(), endpoint, credentialProvider, conf)
         commitBean = intent.getParcelableExtra<CommitBean>("uri") as CommitBean
         res = commitBean.res
        val size = res.size
        btn_fa.setOnClickListener {
            if(titles.text.toString()!=null && texts.text.toString()!=null){
                val decodeString = defaultMMKV!!.decodeString("token")
                var map: HashMap<String, String?> = hashMapOf(Pair("sprout-token",decodeString),
                        Pair("Content-Type","application/json"))
                commitBean.title = titles.text.toString()
                commitBean.mood = texts.text.toString()
                val gson = Gson()
                val obj2: String = gson.toJson(commitBean)
                val body: RequestBody =
                        RequestBody.create(MediaType.parse("application/json"), obj2)

                val retrofitUtil = RetrofitUtil.getInstance()
                retrofitUtil.getBase()
                retrofitUtil.postRequestHeaderBody("trends/submitTrends",map,body,object : INetCallBack<CimmitsBean> {
                    override fun success(t: CimmitsBean?) {
                        if (t != null) {
                            Toast.makeText(this@ShangActivity,t.data.result,Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFail(error: String?) {

                    }

                })
            }else{
                Toast.makeText(this@ShangActivity,"標題和內容不為空",Toast.LENGTH_SHORT).show()
            }
        }
        btn_ok.setOnClickListener {
            btn_fa.isEnabled = true
            count = 0
            val timer = Timer()
            val timertask: TimerTask = timerTask {
                time--
                if (downLoad) {
                    initView(res.get(count).url)
                    text.text = res.get(count).url
                    count++
                    downLoad = false
                    if (count == size) {
                        timer.cancel()
                    }
                }

            }
            timer.schedule(timertask, 100, 300)
        }

    }

    private fun initView(uri: String) {
// 构造上传请求。

        // 构造上传请求。
        val put = PutObjectRequest("demoms", File(uri).name, uri)

// 异步上传时可以设置进度回调。

// 异步上传时可以设置进度回调。
        put.progressCallback = OSSProgressCallback { request, currentSize, totalSize ->
            seek.max = totalSize.toInt()
            seek.progress = currentSize.toInt()
            if (currentSize == totalSize) {
                downLoad = true
                seek.progress = 0
            }
        }

        val task: OSSAsyncTask<*> = oss.asyncPutObject(put, object : OSSCompletedCallback<PutObjectRequest?, PutObjectResult> {
            override fun onSuccess(request: PutObjectRequest?, result: PutObjectResult) {
                val expiration =  Date(System.currentTimeMillis() + 3600 * 10000);
                val urls = oss.presignConstrainedObjectURL("demoms", File(uri).name,expiration.time/100044 ).toString()
              runOnUiThread {
                  commitBean.res.get(count-1).url = urls
              }
                Log.d("PutObject", "UploadSuccess")
                Log.d("ETag", result.eTag)
                Log.d("RequestId", result.requestId)
                Log.d("RequestId", result.serverCallbackReturnBody.toString())
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