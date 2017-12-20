package com.abc.fus.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Arrays extends AppCompatActivity {

    String[] objects = {"张三", "李四", "王五", "赵六", "七"};
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array);

        lv = (ListView) findViewById(R.id.lv);
        //  ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,R.layout.arrays,objects);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.arrays1, R.id.tv_arrays, objects);
        lv.setAdapter(adapter);

    }
}
