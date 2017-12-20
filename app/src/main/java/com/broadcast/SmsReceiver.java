package com.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.abc.fus.myapp.Arrays;

public class SmsReceiver extends BroadcastReceiver {

    // 接收到短信时执行
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        // throw new UnsupportedOperationException("Not yet implemented");

        Object[] objects = (Object[]) intent.getExtras().get("pdus");
        for (Object obj : objects) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj, "3gpp");
            String address = smsMessage.getDisplayOriginatingAddress();
            String message = smsMessage.getDisplayMessageBody();

            Intent intent2 = new Intent(context, Arrays.class);

            intent2.putExtra("address", address);
            intent2.putExtra("message", message);

            Toast.makeText(context, address + ":" + message, Toast.LENGTH_SHORT).show();
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent2);


        }
    }
}
