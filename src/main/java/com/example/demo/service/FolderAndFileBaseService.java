package com.example.demo.service;

import com.example.demo.entity.FolderAndFileBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FolderAndFileBaseService {
    /**
     * @Description 查询对应文件夹下面的文件或文件夹
     * @Author wanxin
     * @Date 2019/10/29 16:13
     * @Param [folderAndFileBaseId]
     * @return java.util.List<com.example.demo.entity.FolderAndFileBase>
     **/
    List<FolderAndFileBase> getFolderAndFileBase(long folderAndFileBaseId);
    /**
     * @Description 进入指定文件夹并分页
     * @Author wanxin
     * @Date 2019/11/1 16:33
     * @Param [condition, id, pageable]
     * @return org.springframework.data.domain.Page<com.example.demo.entity.FolderAndFileBase>
     **/
    Page<FolderAndFileBase> findByFolderPage(String condition, long id, Pageable pageable);
    /**
     * @Description TODO
     * @Author wanxin
     * @Date 2019/11/4 19:21
     * @Param [folderAndFileBase]
     * @return com.example.demo.entity.FolderAndFileBase
     **/
    FolderAndFileBase getById(long folderAndFileBase);
}
