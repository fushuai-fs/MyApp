package com.abc.fus.myapp;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.IOException;
import java.util.List;

import common.OkHttpHelper;

public class OrderListActivity extends AppCompatActivity {
    ListView lv;
    static int itemID =1;
    List<Order> lists ;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_unconfirmed:
                    itemID=1;
                    GetData(itemID);
                    return true;
                case R.id.navigation_hotelunconfirmed:
                    itemID =2;
                    GetData(itemID);
                    return true;
                case R.id.navigation_all:
                    itemID =3;
                    GetData(itemID);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        // listview 添加click事件
          lv = (ListView) findViewById(R.id.lv);
          lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Order order = lists.get(position);
                  long orderID = order.getOrderID();
                  Intent intent = new Intent();
                  intent.putExtra("OrderID",orderID);
                  intent.setClass(getApplicationContext(), Order_Detail_Activity.class);
                  startActivity(intent);
              }
          });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    void GetData(int id)
    {
        final int _id = id;
        Thread thread = new Thread(){

            public void run(){
                String response="";
                String path = "http://localhost:54038/api/Login/Login";
                path = "http://172.16.1.51:91/data/SupplierHandler.ashx";
                String json = "{\"Company\":"+_id+"}";
                try {
                    response = OkHttpHelper.HttpPost(path, json);
                    lists =  JSON.parseArray (response, Order.class );
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
                        lv.setAdapter(new MyAdapter());
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"网络繁忙！请稍后再试",Toast.LENGTH_LONG).show();
                    }
                    break;
            }
            //  showMsg(msg.toString());
            super.handleMessage(msg);
        }
    };

    class  MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return lists.size();
        }
        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if(convertView==null){
                // 获取inflate 的三种方式
                // view = View.inflate(getApplicationContext(),R.layout.items,null);
                //view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.items,null);
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.order_list,null);
            }else{
                view=convertView;
            }
            TextView OrderID = (TextView) view.findViewById(R.id.OrderID);
            TextView HotelName =  (TextView) view.findViewById(R.id.HotelName);
            TextView RoomName =  (TextView) view.findViewById(R.id.RoomName);
            TextView OrderStatus =  (TextView) view.findViewById(R.id.OrderStatus);
            TextView CheckIn =  (TextView) view.findViewById(R.id.CheckIn);
            TextView CheckOut =  (TextView) view.findViewById(R.id.CheckOut);
            TextView Amount =  (TextView) view.findViewById(R.id.Amount);
            TextView Rooms =  (TextView) view.findViewById(R.id.Rooms);
            Order list = lists.get(position);
            OrderID.setText(String.valueOf(list.getOrderID()));
            HotelName.setText(list.getHotelName());
            RoomName.setText(list.getRoomName());
            OrderStatus.setText(list.getOrderStatus());
            CheckIn.setText(list.getCheckIn());
            CheckOut.setText(list.getCheckOut());
            Amount.setText(list.getAmount());
            Rooms.setText(list.getRooms());
          //  System.out.println(position);
            return view;
        }


    }
}
