package com.abc.fus.myapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import common.SQLiteHelper;

public class Registers extends AppCompatActivity {
    EditText userName;
    EditText password;
    EditText phone;
    SQLiteHelper myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registers);

        myHelper = new SQLiteHelper(this);

        Button btn_register = (Button) findViewById(R.id.btn_Register);
        userName = (EditText) findViewById(R.id.et_UserName);
        password = (EditText) findViewById(R.id.et_Password);
        phone = (EditText) findViewById(R.id.et_Phone);

        btn_register.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                String _username = userName.getText().toString().trim();
                String _password = password.getText().toString().trim();
                String _phone = phone.getText().toString().trim();
                if (_username.equals("") || _password.equals("")) {
                    Toast.makeText(getApplicationContext(), "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                SQLiteDatabase db = myHelper.getWritableDatabase();
                Cursor cursor = db.query("account", new String[]{"UserName"}, "UserName=?", new String[]{_username}, null, null, null);
                if (cursor != null && cursor.getCount() != 0) {
                    Toast.makeText(getApplicationContext(), "用户名已经被注册 ！", Toast.LENGTH_SHORT).show();
                    return;
                }
                ContentValues content = new ContentValues();
                content.put("UserName", _username);
                content.put("Password", _password);
                content.put("Phone", _phone);
                long i = db.insert("account", null, content);

                //  Toast.makeText(getApplicationContext(),"_username="+_username+";_password="+_password, Toast.LENGTH_SHORT).show();
                db.close();
                if (i > 0) {
                    Toast.makeText(getApplicationContext(), "注册成功！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "服务器繁忙，请稍后再试！", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
