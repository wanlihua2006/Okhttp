package com.example.wanlihua.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        //getDataAsync();
        getDatasync();
        //postDataWithParams();


    }
    /*异步post*/
private void postDataWithParams(){
    OkHttpClient okHttpClient = new OkHttpClient();
    //FormBody.
    FormBody
}

/*get同步请求*/
private void getDatasync(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //获取okhttpclient 对象
                OkHttpClient okHttpClient = new OkHttpClient();
                //创建Request对象
                Request request = new Request.Builder().url("http://www.baidu.com").build();
                //得到Response对象 并执行

                try {
                    final Response response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()){
                        Log.d("wanlihua","wanlihua debug sync func 数据获取成功 !");
                        Log.d("wanlihua","wanlihua debug request code: " + response.code());
                        Log.d("wanlihua","wanlihua debug request boady string: " + response.body().string());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (response.isSuccessful())
                                    editText.setText("连接百度成功！");
                                else
                                    editText.setText("无网络！");
                            }
                        });
                    }else{
                        Log.d("wanlihua","wanlihua debug sync func 数据获取不成功 !");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            editText.setText("无网络！");
                        }
                    });
                }
            }
        }).start();
}
/*get异步请求*/
private void getDataAsync(){
    //获取okhttpclient 对象
    OkHttpClient okHttpClient = new OkHttpClient();
    //创建Request对象
    Request request = new Request.Builder().url("http://www.baidu.com").build();
    //得到Response对象
    Call call = okHttpClient.newCall(request);
    call.enqueue(new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {
            Log.d("wanlihua","wanlihua debug onFailure !");
        }

        @Override
        public void onResponse(Response response) throws IOException {
            Log.d("wanlihua","wanlihua debug onResponse !");
            if (response.isSuccessful()){
                Log.d("wanlihua","wanlihua debug 数据获取成功 !");
                Log.d("wanlihua","wanlihua debug request code: " + response.code());
                Log.d("wanlihua","wanlihua debug request boady string: " + response.body().string());
            }
        }
    });
    }
}
