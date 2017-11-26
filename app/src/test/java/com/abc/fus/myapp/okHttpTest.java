package com.abc.fus.myapp;

import org.junit.Test;

import common.OkHttpHelper;
import common.StringHelper;

import com.alibaba.fastjson.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by FUSHUAI on 2017/10/1.
 */

public class okHttpTest {

    @Test
    public void addition_isCorrect() throws Exception {
        String path = "http://localhost:38047/TestHandler.ashx";
        String json = "{\"abc\":\"12345\"}";

        JSONObject jsobj =  JSON.parseObject(json);

        System.out.println(jsobj.get("abc"));

        String Baitour = StringHelper.getMD5("Baitour");

        System.out.println("1111111111");

        System.out.println(Baitour);

        String response = OkHttpHelper.HttpPost(path, json);

        System.out.println("1111111111");

        System.out.println(response);
    }

}
