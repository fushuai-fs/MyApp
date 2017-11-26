package common;

/**
 * Created by FUSHUAI on 2017/10/1.
 */

import java.io.IOException;

import okhttp3.*;

/* 简单的http封装 */
public class OkHttpHelper {
    private static final OkHttpClient client = new OkHttpClient();

    public static String HttpGet(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /*POST提交Json数据*/
//  public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//    public static String HttpPost(String url, String json) throws IOException {
//        RequestBody body = RequestBody.create(JSON, json);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//
//        Response response = client.newCall(request).execute();
//        if (response.isSuccessful()) {
//            return response.body().string();
//        } else {
//            throw new IOException("Unexpected code " + response);
//        }
//    }
    /*POST提交form数据*/
    public static String HttpPost(String url, String data) throws IOException {
//        String SignId = "Baitour";

        RequestBody formBody = new FormBody.Builder()
                .add("SignId","9CC6161EAC0C3AB79A0EA6BD051F49CA").add("data", data)
                .build();
        Request request = new Request.Builder().addHeader("Company","Baitour")
                .url(url)
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            return response.body().string();
        } else {
           // throw new IOException("Unexpected code " + response);
            return "";
        }
    }


}