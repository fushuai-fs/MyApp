package com.abc.fus.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViews extends AppCompatActivity {
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

          lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new myListAdapter());
    }

    public  class  myListAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return 600;
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
            TextView tv;
            if(convertView==null){tv=new TextView(getApplicationContext());}else{tv = (TextView) convertView;}
            tv.setText("1234567890."+position);
            System.out.println(position);
            return tv;
        }
    }
}
