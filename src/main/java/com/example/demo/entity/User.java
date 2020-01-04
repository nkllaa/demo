package com.example.demo.entity;



import com.example.demo.entity.enumObj.BooleanEnum;
import com.example.demo.exception.BizException;
import org.springframework.util.DigestUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,length = 35)
    private String userName;
    @Column(nullable = true,length = 35)
    private String password;
    //电话
    @Column(length = 35)
    private String phoneNumber;
    //是否有效
    private BooleanEnum isEffective;
    private Date createData;
    //用户使用空间单位GB
    private BigDecimal useSpace;
    //用户总空间单位GB
    private BigDecimal totalSpace;
    //是否登录
    private BooleanEnum isLogin;
    //本次登录时间
    private Date loginDate;
    //本初退出时间
    private Date loginOutDate;
    private String openId;
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getLoginOutDate() {
        return loginOutDate;
    }

    public void setLoginOutDate(Date loginOutDate) {
        this.loginOutDate = loginOutDate;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Date getCreateData() {
        return createData;
    }

    public void setCreateData(Date createData) {
        this.createData = createData;
    }

    public BigDecimal getUseSpace() {
        return useSpace;
    }

    public void setUseSpace(BigDecimal useSpace) {
        this.useSpace = useSpace;
    }

    public BooleanEnum getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(BooleanEnum isLogin) {
        this.isLogin = isLogin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BooleanEnum getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(BooleanEnum isEffective) {
        this.isEffective = isEffective;
    }
    public BigDecimal getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(BigDecimal totalSpace) {
        this.totalSpace = totalSpace;
    }

    public User() {
    }

    public void create(String userName, String phoneNumber, String password){
        if (userName==null||userName.equals("")){
            throw new BizException("用户名不能为空");
        }
        if (phoneNumber==null||phoneNumber.equals("")){
            throw new BizException("电话号码不能为空");
        }
        if (password==null||password.equals("")){
            throw new BizException("密码不能为空");
        }
        String str=phoneNumber+password;
        //加密的密码
        this.password= DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(str.getBytes()).getBytes());
        this.userName=userName;
        this.phoneNumber=phoneNumber;
        this.isEffective=BooleanEnum.YES;
        this.createData=new Date();
        this.totalSpace=new BigDecimal(5);
        this.useSpace=BigDecimal.ZERO;
    }
    /**
     * @Description 修改登录状态
     * @Author wanxin
     * @Date 2019/6/16 10:39
     * @Param [isLogin]
     * @return void
     **/
    public void updataLogin(){
        this.loginDate=new Date();
        this.isLogin=BooleanEnum.YES;
    }
    public void loginOut(){
        this.loginOutDate=new Date();
        this.isLogin=BooleanEnum.NO;
    }
    /**
     * @Description 文件上传时计算剩余空间
     * @Author wanxin
     * @Date 2020/1/2 16:10
     * @Param [size]
     * @return void
     **/
    public void addFileSize(BigDecimal size){
        BigDecimal useSpace=this.useSpace.add(size);
        if (this.useSpace.compareTo(this.totalSpace)== 1){
            throw new BizException("文件太大，装不下啦！");
        }
        this.useSpace=useSpace;
    }
    /**
     * @Description 文件删除时计算剩余空间
     * @Author wanxin
     * @Date 2020/1/2 16:10
     * @Param [size]
     * @return void
     **/
    public void reduceFileSize(BigDecimal size){
        BigDecimal useSpace=this.useSpace.subtract(size);
        this.useSpace=useSpace;
    }
}
