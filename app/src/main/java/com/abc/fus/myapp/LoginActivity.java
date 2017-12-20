package com.abc.fus.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import common.OkHttpHelper;
import common.SQLiteHelper;

public class LoginActivity extends AppCompatActivity {
    SQLiteHelper myHelper;
    Button btn_Login;
    EditText company;
    EditText userName;
    EditText password;
    Button btn_Login2;
    Button btn_Login3;
    Button btn_Login4;
    Button btn_Login5;
    Button btn_Login6;
    Button btn_Register;

    Button btn_showData;
    Button btn_sign;

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (msg.obj.toString() != "") {
                        Intent intent = new Intent();
                        intent.setClass(getApplicationContext(), OrderListActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "登录失败，请重新登录！", Toast.LENGTH_LONG).show();
                    }
                    break;
            }
            //  showMsg(msg.toString());
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);


        btn_sign = (Button) findViewById(R.id.btn_sign);
//        btn_Login = (Button) findViewById(R.id.btn_Login);
//        btn_Login2 = (Button) findViewById(R.id.btn_Login2);
//        btn_Login3= (Button) findViewById(R.id.btn_Login3);
//        btn_Login4= (Button) findViewById(R.id.btn_Login4);
//        btn_Login5= (Button) findViewById(R.id.btn_Login5);
//        btn_Login6= (Button) findViewById(R.id.btn_Login6);
//        btn_Register= (Button) findViewById(R.id.btn_Register);
//        btn_showData= (Button) findViewById(R.id.btn_showData);

        userName = (EditText) findViewById(R.id.et_UserName);
        password = (EditText) findViewById(R.id.et_Password);
        company = (EditText) findViewById(R.id.et_Company);
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _company = company.getText().toString().trim();
                String _userName = userName.getText().toString().trim();
                String _password = password.getText().toString().trim();
                if (_company.equals("") || _userName.equals("") || _password.equals("")) {
                    Toast.makeText(getApplicationContext(), "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    onServerLogin(_company, _userName, _password);
                }
            }
        });
//        btn_Login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               if(userName.getText().toString().trim().equals("") || password.getText().toString().trim().equals(""))
//               {
//                   Toast.makeText(getApplicationContext(), "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
//                   return;
//               }
//              boolean boo = onLogin(userName.getText().toString().trim(),password.getText().toString().trim());
//                if(!boo) {
//                    return;
//                }
//               Intent intent = new Intent();
//                intent.setClass(getApplicationContext(), CallPhone.class);
//                startActivityForResult(intent, 0);
//               // startActivity(intent);
//               finish();
//                //Toast.makeText(getApplicationContext(), String.format("UserName=%s-- password=%s", userName.getText(), password.getText()), LENGTH_SHORT).show();
//
//            }
//        });
//        btn_Login2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(userName.getText().toString().trim().equals("") || password.getText().toString().trim().equals(""))
//                {
//                    Toast.makeText(getApplicationContext(), "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Intent intent = new Intent();
//                intent.setClass(getApplicationContext(), ListViews.class);
//                startActivityForResult(intent, 0);
//                // startActivity(intent);
//                finish();
//                //Toast.makeText(getApplicationContext(), String.format("UserName=%s-- password=%s", userName.getText(), password.getText()), LENGTH_SHORT).show();
//            }
//        });

//        btn_Login3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(userName.getText().toString().trim().equals("") || password.getText().toString().trim().equals(""))
//                {
//                    Toast.makeText(getApplicationContext(), "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Intent intent = new Intent();
//                intent.setClass(getApplicationContext(),Arrays.class);
//                startActivityForResult(intent, 0);
//                // startActivity(intent);
//                finish();
//                //Toast.makeText(getApplicationContext(), String.format("UserName=%s-- password=%s", userName.getText(), password.getText()), LENGTH_SHORT).show();
//
//            }
//        });

//        btn_Login4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(userName.getText().toString().trim().equals("") || password.getText().toString().trim().equals(""))
//                {
//                    Toast.makeText(getApplicationContext(), "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Intent intent = new Intent();
//                  intent.setClass(getApplicationContext(), Inflates.class);
//                startActivityForResult(intent, 0);
//                // startActivity(intent);
//              //  finish();
//                //Toast.makeText(getApplicationContext(), String.format("UserName=%s-- password=%s", userName.getText(), password.getText()), LENGTH_SHORT).show();
//
//            }
//        });
//        btn_Login5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(userName.getText().toString().trim().equals("") || password.getText().toString().trim().equals(""))
//                {
//                    Toast.makeText(getApplicationContext(), "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Intent intent = new Intent();
//                intent.setClass(getApplicationContext(),SimplesAdapter.class);
//                startActivityForResult(intent, 0);
//                // startActivity(intent);
//               // finish();
//                //Toast.makeText(getApplicationContext(), String.format("UserName=%s-- password=%s", userName.getText(), password.getText()), LENGTH_SHORT).show();
//
//            }
//        });


//        btn_showData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(getApplicationContext(),showDatabaseData.class);
//               // startActivityForResult(intent, 0);
//                 startActivity(intent);
//                // finish();
//                //Toast.makeText(getApplicationContext(), String.format("UserName=%s-- password=%s", userName.getText(), password.getText()), LENGTH_SHORT).show();
//
//            }
//        });


//        btn_Register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent();
//                intent.setClass(getApplicationContext(),Registers.class);
//                startActivityForResult(intent, 0);
//                // startActivity(intent);
//                // finish();
//                //Toast.makeText(getApplicationContext(), String.format("UserName=%s-- password=%s", userName.getText(), password.getText()), LENGTH_SHORT).show();
//
//            }
//        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void showMsg(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplication(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    //    protected boolean onLogin(String userName,String password)
//    {
//         myHelper =new SQLiteHelper(getApplicationContext());
//        SQLiteDatabase db = myHelper.getReadableDatabase();
//        Cursor account = db.query("account", new String[]{"_id", "UserName"}, "UserName=? and password=?", new String[]{userName, password}, null, null, null);
//        if(account !=null && account.getCount()>0)
//        {
//            return  true;
//        }else
//        {
//            Toast.makeText(getApplicationContext(),"登录失败！",Toast.LENGTH_SHORT).show();
//            return  false;
//        }
//    }
    protected void onServerLogin(String Company, String userName, String password) {
        final String _company = Company;
        final String _userName = userName;
        final String _password = password;
        Thread thread = new Thread() {

            public void run() {
                String response = "";
                String path = "http://localhost:54038/api/Login/Login";
                path = "http://172.16.1.51:91/data/SupplierHandler.ashx";
                String json = "{\"Company\":\"" + _company + "\",\"UserName\":\"" + _userName + "\",\"PassWord\":\"" + _password + "\"}";
                try {
                    response = OkHttpHelper.HttpPost(path, json);
                } catch (IOException e) {
                    showMsg(e.toString());
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.what = 1;
                msg.obj = response;
                myHandler.sendMessage(msg);
            }
        };
        thread.start();

    }
}

