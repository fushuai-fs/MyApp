package com.abc.fus.myapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CallPhone extends AppCompatActivity {
    EditText phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_phone);
          phone = (EditText) findViewById(R.id.et_Phone);
        phone.setInputType(EditorInfo.TYPE_CLASS_PHONE);
         //  btnCallPhone = (Button) findViewById(R.id.btn_CallPhone);
    }

   public void CallPhone(View v)
    {
        String phoneNo =phone.getText().toString().trim();
        if(phoneNo.equals(""))
        {
            Toast.makeText(getApplicationContext(), "请输入电话号码！", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(getApplicationContext(), phoneNo, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+ phoneNo));
        startActivity(intent);
    }
}
