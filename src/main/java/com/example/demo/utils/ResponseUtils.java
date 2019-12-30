package com.example.demo.utils;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {
    public static final String SUCCESS="success";
    public static final String Fail="fail";
    public Map<String ,Object> setResponse(String response,String msg){
        Map<String ,Object> map=new HashMap<>();
        map.put("response",response);
        map.put("msg",msg);
        return map;
    }
    public Map<String ,Object> setResponse(String response,String msg,Object object){
        Map<String ,Object> map=new HashMap<>();
        map.put("response",response);
        map.put("msg",msg);
        map.put("data",object);
        return map;
    }
    public String getJSONArrayToString(JSONArray ja){
        return ja.toString();
    }
    public String getJSONObjectToString(JSONObject jo){
        return jo.toString();
    }

}
