package com.abc.fus.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class Inflates extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflate);

        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new myListAdapter());

    }

    public class myListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 7;
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
            if (convertView == null) {
                // 获取inflate 的三种方式
                // view = View.inflate(getApplicationContext(),R.layout.items,null);
                //view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.items,null);
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.items, null);
            } else {
                view = convertView;
            }

            System.out.println(position);
            return view;
        }
    }
}
