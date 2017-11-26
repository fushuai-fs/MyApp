package common;

import android.support.v7.app.AppCompatActivity;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by FUSHUAI on 2017/10/2.
 */

public class AppInstrumentedActivity extends AppCompatActivity {
    public AppInstrumentedActivity() {
    }

    public void onStart() {
        super.onStart();
    }

    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    public void onStop() {
        super.onStop();
    }
}
