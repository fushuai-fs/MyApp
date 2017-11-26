package common;

import android.app.Application;
import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by FUSHUAI on 2017/10/2.
 */

public class MyApplication extends Application {
    private static final String TAG = "JPush";

    @Override
    public void onCreate() {
        Logger.d(TAG, "[MyApplication] onCreate");
        super.onCreate();

        Set<String> set = new HashSet<String>();
        JPushInterface.setTags(getApplicationContext(), 0, set);
        JPushInterface.setAlias(getApplicationContext(),0,"");
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush  初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
    }

}
