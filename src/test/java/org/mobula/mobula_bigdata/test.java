/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package org.mobula.mobula_bigdata;

import java.util.ArrayList;
import java.util.HashMap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class test {
    public static void main (String[] args) {
        ArrayList list = new ArrayList();
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("1");
        jsonArray.add("2");
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("1","2");
        jsonObject.put("1","2");
        HashMap map = new HashMap();
        map.put("aaa","1");
        map.put("bbb", "2");
        map.put("arr", jsonArray);
        map.put("ccc",new ArrayList<>());
        jsonObject.putAll(map);
        jsonObject.put("json",jsonObject1);
        System.out.println(jsonObject);
    }
}
