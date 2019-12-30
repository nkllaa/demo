package com.example.demo.entity;

import com.example.demo.entity.enumObj.BooleanEnum;
import com.example.demo.entity.enumObj.FileTypeEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//用户文件
@Entity
@DiscriminatorValue("UserFile")
public class UserFile extends FolderAndFileBase implements Serializable {

    //文件类型
    private FileTypeEnum fileTypeEnum;

    public UserFile() {
    }

    public UserFile(double size, BooleanEnum isEffective, Date createDate, String path, String name, User user, FileTypeEnum fileTypeEnum) {
        super(size, isEffective, createDate, path, name, user);
        this.fileTypeEnum = fileTypeEnum;
    }

    public UserFile(FileTypeEnum fileTypeEnum) {
        this.fileTypeEnum = fileTypeEnum;
    }

    public FileTypeEnum getFileTypeEnum() {
        return fileTypeEnum;
    }

    public void setFileTypeEnum(FileTypeEnum fileTypeEnum) {
        this.fileTypeEnum = fileTypeEnum;
    }

    /**
     * @Description 创建
     * @Author wanxin
     * @Date 2019/8/25 13:28
     * @Param [fileNames, folderPath]
     * @return com.example.demo.entity.UserFile
     **/
    public void create(String fileNames, String path,Folder folder,FileTypeEnum fileTypeEnum) {
        this.name=fileNames;
        this.path=path+"\\"+this.name;
        this.createDate=new Date();
        this.isEffective=BooleanEnum.YES;
        this.fileTypeEnum=fileTypeEnum;
        this.folderAndFileBase=folder;
    }
}
