package com.example.demo.entity;

import com.example.demo.entity.enumObj.BooleanEnum;
import com.example.demo.exception.BizException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//用户文件夹
@Entity
@DiscriminatorValue("Folder")
public class Folder extends FolderAndFileBase implements Serializable {
    //是否是根目录
    private boolean isRoot;

    public boolean getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(boolean root) {
        isRoot = root;
    }

    public void create(String folderPath, String folderName, Folder folder, User user){
        if (folderPath==null||"".equals(folderPath)){
            throw new BizException("文件夹路径不能为空");
        }
        if (folderName==null|| "".equals(folderName)){
            throw new BizException("文件夹名不能为空");
        }
        if (user==null){
            throw new BizException("所属用户不能为空");
        }
        this.user=user;
        this.path=folderPath;
        this.name=folderName;
        this.isEffective=BooleanEnum.YES;
        this.createDate=new Date();
        this.folderAndFileBase=folder;
        if (folder==null){
            this.isRoot=true;
        }
    }
}
