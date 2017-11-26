package com.abc.fus.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimplesAdapter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adapter);

        ListView lv = (ListView) findViewById(R.id.lv);
        List<Map<String ,String>> data =new ArrayList<Map<String ,String>>();
        Map<String ,String> map1 =new HashMap<String,String>();
        map1.put("name","张三");
        map1.put("phone","1388888");
        Map<String ,String> map2 =new HashMap<String,String>();
        map2.put("name","张飞");
        map2.put("phone","1388888");
        Map<String ,String> map3 =new HashMap<String,String>();
        map3.put("name","赵云");
        map3.put("phone","1388888");
        Map<String ,String> map4 =new HashMap<String,String>();
        map4.put("name","项羽");
        map4.put("phone","1388888");

        data.add(map1);
        data.add(map2);
        data.add(map3);
        data.add(map4);

        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
                data,R.layout.simples,new String[]{"name","phone"},new int[]{R.id.tv_name,R.id.tv_phone});

        lv.setAdapter(adapter);
    }
}
