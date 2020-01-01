package com.example.demo.controller.wxxcx;

import com.example.demo.BizService.BizUserService;
import com.example.demo.entity.User;
import com.example.demo.entity.enumObj.FileTypeEnum;
import com.example.demo.utils.ResponseUtils;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;


@RestController
@RequestMapping("/userXCX")
public class UserController extends ResponseUtils{
    @Autowired
    @Qualifier("bizUserService")
    BizUserService bizUserService;

    /**
     * @Description 用户登录
     * @Author wanxin
     * @Date 2019/5/26 17:47
     * @Param [reqData]
     * @return java.lang.String
     **/
    @RequestMapping("/userLogin")
    public Map<String ,Object> userLogin(String phoneNumber, String password,
                                         String iv, String encryptedData, String code,
                                         String session_3rd, HttpServletRequest request){
        JSONObject jo=bizUserService.wxUusetLogin(phoneNumber,password,iv,encryptedData,code,session_3rd,request.getSession().getId());
        return this.setResponse("success","登录成功",jo);
    }
    /**
     * @Description 用户注册
     * @Author wanxin
     * @Date 2019/5/25 16:21
     * @Param [userName, phoneNumber, password]
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("/userRegister")
    public  Map<String ,Object>  userRegister(String userName,String phoneNumber,String password) throws IOException {
        JSONObject jo =bizUserService.userRegister(userName,phoneNumber,password);
        return this.setResponse("success","注册成功",jo);
    }
    /**
     * @Description 用户文件上传
     * @Author wanxin
     * @Date 2019/6/7 12:42
     * @Param [uploadFile, userId, folderId, fileType]
     * @return java.lang.String
     **/
    /*public  Map<String ,Object>  upload(MultipartFile uploadFile, long userId, long folderId, int fileType){
        JSONObject jo=bizUserService.upload(uploadFile,userId,folderId,fileType);
        return this.getResponse("success","上传成功",jo);
    }*/
}
