package com.abc.fus.myapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import common.SQLiteHelper;

public class showDatabaseData extends AppCompatActivity {
    SQLiteHelper myHelper;
    List<Account> accounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_database_data);


        myHelper = new SQLiteHelper(this);
        myHelper.getWritableDatabase();
        accounts = new ArrayList<Account>();

        findViewById(R.id.btn_show);
    }

    public void btnShow_Click(View v) {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        Cursor cursor = db.query("account", null, null, null, null, null, null);
        accounts.clear();

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Integer id = cursor.getInt(0);
                String userName = cursor.getString(1);
                String phone = cursor.getString(2);

                Account account = new Account();
                account.setID(id);
                account.setUserName(userName);
                account.setPhone(phone);
                accounts.add(account);
            }
        }
        db.close();

        Toast.makeText(getApplicationContext(), "成功！", Toast.LENGTH_SHORT).show();
        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return accounts.size();
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
                view = inflater.inflate(R.layout.simples, null);
            } else {
                view = convertView;
            }
            TextView tv_id = (TextView) view.findViewById(R.id.tv_id);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            TextView tv_phone = (TextView) view.findViewById(R.id.tv_phone);
            Account account = accounts.get(position);
            tv_id.setText(account.getID().toString());
            tv_name.setText(account.getUserName());
            tv_phone.setText(account.getPhone());

            System.out.println(position);
            return view;
        }
    }

}
