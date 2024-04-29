package com.funshow.customspinner_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Spinner
import com.funshow.customspinner_kotlin.Adapter.Serverlist_spinner_adapter
import com.funshow.customspinner_kotlin.Data.ServiceListData
import org.json.JSONArray
import org.json.JSONObject

//首頁
class MainActivity : AppCompatActivity() {

    //基本宣告
    lateinit var server: Spinner
    private val ServiceListDataArrayList = ArrayList<ServiceListData>() //伺服器 ArrayList
    var result = "    [\n" +
            "    {\n" +
            "        \"name\": \"test1\",\n" +
            "        \"site_url\": \"https://test1.money.com.tw\",\n" +
            "        \"app_live_websocket\": \"wss://stream.hotsnet.com/test1\",\n" +
            "        \"type_live_third_party_vendor\": \"facebook\",\n" +
            "        \"web_msg_websocket\": \"sock-01.168money.com.tw:10003\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"test2\",\n" +
            "        \"site_url\": \"https://test1.money.com.tw\",\n" +
            "        \"app_live_websocket\": \"wss://stream.hotsnet.com/test1\",\n" +
            "        \"type_live_third_party_vendor\": \"facebook\",\n" +
            "        \"web_msg_websocket\": \"sock-02.168money.com.tw:10002\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"test3\",\n" +
            "        \"site_url\": \"https://test1.money.com\",\n" +
            "        \"app_live_websocket\": \"wss://money.hotsnet.com/test1\",\n" +
            "        \"type_live_third_party_vendor\": \"facebook\",\n" +
            "        \"web_msg_websocket\": \"sock-03.money.com:10021\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"test14\",\n" +
            "        \"site_url\": \"https://test1.money.com\",\n" +
            "        \"app_live_websocket\": \"wss://streamsg.money.com/test1\",\n" +
            "        \"type_live_third_party_vendor\": \"facebook\",\n" +
            "        \"web_msg_websocket\": \"sock-my.money.com:10009\" }\n" +
            "    ]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById() //程式和 xml 關聯
        setSpinner() //設定下拉選單
    }

    //程式和 xml 關聯
    fun findViewById() {
        server = findViewById(R.id.server)
    }

    //設定 Spinner
    fun setSpinner() {
        getServiceList() //獲取伺服器列表
    }

    //獲取伺服器列表
    fun getServiceList() {
        //解 JSONArray
        val result_array = JSONArray(result)
        for(i in 0 until result_array.length()){
            //解 JSONArray
            var jsonObject = JSONObject(result_array.getString(i))

            //建立容器
            var servicelistdata = ServiceListData()

            //將資料導入容器
            servicelistdata.name = if(!jsonObject.getString("name").isNotEmpty()) "" else jsonObject.getString("name") //伺服器名字
            servicelistdata.site_url = if(!jsonObject.getString("site_url").isNotEmpty()) "" else jsonObject.getString("site_url") //座標 url
            servicelistdata.app_live_websocket = if(!jsonObject.getString("app_live_websocket").isNotEmpty()) "" else jsonObject.getString("app_live_websocket").toString() //直播 websocket
            servicelistdata.type_live_third_party_vendor = if(!jsonObject.getString("type_live_third_party_vendor").isNotEmpty()) "" else jsonObject.getString("type_live_third_party_vendor").toString() //種類
            servicelistdata.web_msg_websocket = if(!jsonObject.getString("web_msg_websocket").isNotEmpty()) "" else jsonObject.getString("web_msg_websocket") //網頁消息 websocket
            ServiceListDataArrayList.add(servicelistdata)
        }

        val spinnerAdapter = Serverlist_spinner_adapter(this, ServiceListDataArrayList)
        server.adapter = spinnerAdapter
    }
}