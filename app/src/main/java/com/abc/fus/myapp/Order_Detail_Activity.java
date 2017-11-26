package com.abc.fus.myapp;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

import common.OkHttpHelper;

public class Order_Detail_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Intent intent = getIntent();
        if(intent == null)
        { finish();}
        else {
            long orderID = intent.getLongExtra("OrderID", 0);
            GetData(orderID);
        }
    }
    void GetData(long id)
    {
        final long _id = id;
        Thread thread = new Thread(){

            public void run(){
                String response="";
                String path = "http://localhost:54038/api/Login/Login";
                path = "http://172.16.1.51:91/data/SupplierHandler.ashx";
                String json = "{\"Company\":"+_id+"}";
                try {
                    response = OkHttpHelper.HttpPost(path, json);
                    // lists = JSON.parseObject(response,new TypeReference<List<Order>>(){});
                } catch (IOException e) {
                    showMsg(e.toString()); e.printStackTrace();
                    response="";
                }
                Message msg = new Message();
                msg.what=1;
                msg.obj=response;
                myHandler.sendMessage(msg);
            }
        };
        thread.start();
    }
    private void showMsg(final  String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplication(),msg,Toast.LENGTH_LONG).show();
            }
        });
    }
    Handler myHandler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if(msg.obj.toString()!="")
                    {
                        Order lists =  JSON.parseObject (msg.obj.toString(), Order.class );
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"网络繁忙！请稍后再试",Toast.LENGTH_LONG).show();
                        finish();
                    }
                    break;
            }
            //  showMsg(msg.toString());
            super.handleMessage(msg);
        }
    };
}
