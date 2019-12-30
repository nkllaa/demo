package com.example.demo.service;

import com.example.demo.entity.Folder;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FolderService {
    /**
     * @Description 创建用户文件夹
     * @Author wanxin
     * @Date 2019/6/2 16:56
     * @Param [user, path, folderName]
     * @return com.example.demo.entity.Folder
     **/
    Folder create(User user, String path,String folderName);
    /**
     * @Description 通过id查询
     * @Author wanxin
     * @Date 2019/6/2 17:38
     * @Param [folderId]
     * @return com.example.demo.entity.Folder
     **/
    Folder getById(long folderId);
    
    /**
     * @Description 查询用户的文件夹
     * @Author wanxin
     * @Date 2019/8/21 20:09 
     * @Param [userId]
     * @return java.util.List<com.example.demo.entity.Folder>
     **/
    List<Folder> getByUserId(long userId);

    /**
     * @Description 查询用户根目录
     * @Author wanxin
     * @Date 2019/8/22 14:10
     * @Param [userId]
     * @return com.example.demo.entity.Folder
     **/
    Folder getUserFolderAndFileRoot(long userId);

    /**
     * @Description 根据用户id和父级目录id查询
     * @Author wanxin
     * @Date 2019/8/22 14:36
     * @Param [folderId, userId]
     * @return java.util.List<com.example.demo.entity.Folder>
     **/
    List<Folder> getByFolderIdAndUserId(long folderId, long userId);

    /**
     * @Description 创建文件夹
     * @Author wanxin
     * @Date 2019/10/30 19:05
     * @Param [newFolder]
     * @return void
     **/
    void save(Folder newFolder);

    /**
     * @Description 根据文件夹名和上层文件夹id查询
     * @Author wanxin
     * @Date 2019/10/30 20:19
     * @Param [name]
     * @return com.example.demo.entity.Folder
     **/
    Folder getByNameAndFolder(String name,long folderId);
    /**
     * @Description 进入指定文件夹并分页
     * @Author wanxin
     * @Date 2019/11/1 14:55
     * @Param [condition, folderId]
     * @return org.springframework.data.domain.Page<com.example.demo.entity.Folder>
     **/
    Page<Folder> findByFolderPage(String condition,long folderId,Pageable pageable);
}
