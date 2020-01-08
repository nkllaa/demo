package com.example.demo.BizServiceImpl;

import com.example.demo.BizService.BizUserService;
import com.example.demo.entity.Folder;
import com.example.demo.entity.FolderLogger;
import com.example.demo.entity.User;

import com.example.demo.entity.enumObj.BooleanEnum;
import com.example.demo.exception.BizException;
import com.example.demo.pojo.Wx3re_Session;
import com.example.demo.service.FolderLoggerService;
import com.example.demo.service.FolderService;
import com.example.demo.service.UserFileService;
import com.example.demo.service.UserService;
import com.example.demo.utils.RedisUtil;
import com.example.demo.utils.SessionUtil;
import com.example.demo.utils.TokenUtils;
import com.example.demo.utils.WXRequestUtils;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service("bizUserService")
public class BizUserServiceImpl implements BizUserService {
    @Autowired
    @Qualifier("userService")
    private UserService userService;
    @Autowired
    @Qualifier("folderService")
    private FolderService folderService;
    @Autowired
    @Qualifier("userFileService")
    private UserFileService userFileService;
    @Autowired
    private WXRequestUtils wxRequestUtils;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private FolderLoggerService folderLoggerService;

    public JSONObject userRegister(String userName,String phoneNumber,String password){
        User user1=userService.getByPhoneNumber(phoneNumber);
        if (user1!=null){
            throw new BizException("该手机号已经被注册");
        }
        User user2=userService.getByUserName(phoneNumber);
        if (user2!=null){
            throw new BizException("该用户名已注册");
        }
        //用户注册
        User user=userService.create(userName,phoneNumber,password);
        //创建用户根文件夹
        String path="e:\\XYP\\User\\"+user.getUserName()+user.getId();
        String folderName=user.getUserName()+user.getId();
        Folder folder =folderService.create(user,path,folderName);

        //创建文件夹操作记录
        folderLoggerService.save(user,folder, BooleanEnum.YES,BooleanEnum.NO,BooleanEnum.NO,"用户注册创建根目录");

        File file=new File(path);
        //创建文件夹
        file.mkdirs();
        JSONObject jo=new JSONObject();
        jo.put("id",user.getId());
        return  jo;
    }
    public JSONObject usetLogin(String phoneNumber, String password, HttpServletRequest request) {
        JSONObject jo=new JSONObject();
        User user=userService.usetLogin(phoneNumber,password);
        jo.put("userName",user.getId());
        jo.put("userId",user.getId());
        jo.put("phoneNumber",user.getPhoneNumber());

        //存入session
            request.getSession().setAttribute("userInfo" ,user);

        return jo;
    }
    //wx小程序登录
    public JSONObject wxUserLogin(String phoneNumber, String password,String iv,String encryptedData,String code,String session_3rd,String sessionId) {
        JSONObject jo=new JSONObject();
        //是否登录
        boolean flag=true;
        if (session_3rd!=null && !"".equals(session_3rd.trim())){
            Object object=redisUtil.get(session_3rd);
            if (object!=null){
                Wx3re_Session wx3re_session=(Wx3re_Session) object;
                jo.put("userId",wx3re_session.getUserId());
                jo.put("userName",wx3re_session.getUserName());
                jo.put("session_3rd",wx3re_session.getSession_3rd());
                jo.put("phoneNumber",wx3re_session.getPhoneNum());
                flag=false;
            }
        }
        if (flag){
            User user=userService.usetLogin(phoneNumber,password);
            JSONObject wxJa=wxRequestUtils.WXUserLogin(iv,encryptedData,code);

            String str=SessionUtil.generateSessionId();

            Wx3re_Session wx3re_session=new Wx3re_Session(wxJa.getString("openid"),
                    wxJa.getString("session_key"),sessionId,user.getId(),user.getPhoneNumber(),
                    str,user.getUserName());

            tokenUtils.save(str,wx3re_session);

            user.setOpenId(wxJa.getString("openid"));
            userService.updata(user);
            jo.put("userId",user.getId());
            jo.put("userName",user.getUserName());
            jo.put("phoneNumber",user.getPhoneNumber());
            jo.put("session_3rd",str);
        }
        return jo;
    }
    public JSONObject getUserInfo(long userId) {
        User user=userService.getById(userId);
        if (user==null){
            throw new BizException("没有找到对应用户");
        }
        JSONObject jo=new JSONObject();

        BigDecimal percentage=user.getUseSpace().divide(user.getTotalSpace(),5, RoundingMode.HALF_UP).
                multiply(new BigDecimal(100));

        jo.put("useSpace",user.getUseSpace());
        jo.put("surplus",user.getTotalSpace().subtract(user.getUseSpace()));
        jo.put("percentage",percentage);

        return jo;
    }
    public JSONObject getLately(long userId) {
        JSONObject jo=new JSONObject();



        /*jo.put("fileId",);
        jo.put("fileName",);
        jo.put("filePath",);
        jo.put("fileType",);
        jo.put("fileCreateDate",);*/



        return jo;
    }
}
