package com.example.demo.serviceImpl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.exception.BizException;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public User create(String userName,String phoneNumber,String password) {
        User user=new User();
        user.create(userName,phoneNumber,password);
        return userDao.save(user);
    }
    public User usetLogin(String phoneNumber, String password) {

         User user=userDao.findByPhoneNumber(phoneNumber);
         if (user==null){
             throw new BizException("没有该账号信息");
         }

        String str=phoneNumber+password;
         //加密的密码
        String encryption= DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(str.getBytes()).getBytes());

         if(!user.getPassword().equals(encryption)){
             throw new BizException("用户名或密码错错误");
         }
         user.updataLogin();

         this.updata(user);

         return user;
    }
    public User getByPhoneNumber(String phoneNumber) {
        return userDao.findByPhoneNumber(phoneNumber);
    }
    public User getByUserName(String userName) {
        return userDao.findByUserName(userName);
    }
    public void updata(User user) {
        userDao.save(user);
    }
    public User getById(long userId) {
        return userDao.getById(userId);
    }
}
