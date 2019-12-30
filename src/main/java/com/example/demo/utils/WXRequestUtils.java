package com.example.demo.utils;

import com.example.demo.config.DataConfig;
import com.example.demo.exception.BizException;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class WXRequestUtils {
    public JSONObject WXUserLogin(String encryptedData,String iv,String  code){
        if(code==null || "".equals(code)){
            throw  new BizException("请求参数不正确");
        }
        String parmars="appId="+DataConfig.appId+"&secret="+DataConfig.AppSecret+"&js_code="+code+"&grant_type="+DataConfig.grant_type;
        String result= HttpRequestUtils.sendPost(DataConfig.url,parmars);
        JSONObject resultJo=JSONObject.fromObject(result);
        if ( resultJo.get("errcode")!=null){
          throw new BizException("登录失败，请稍后在式");
        }
        return resultJo;
    }
}
