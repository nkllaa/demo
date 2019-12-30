package com.example.demo.dao;

import com.example.demo.entity.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserFileDao extends JpaRepository<UserFile,Long>,JpaSpecificationExecutor {
    /**
     * @Description 查询对应文件夹下的文件
     * @Author wanxin
     * @Date 2019/8/22 14:45
     * @Param [folderId, userId]
     * @return java.util.List<com.example.demo.entity.UserFile>
     **/
    @Query(value = "from UserFile where folderAndFileBase.id=:folderId")
    List<UserFile> getByFolderIdAndUserId(long folderId);
    /**
     * @Description 根据id查询
     * @Author wanxin
     * @Date 2019/11/3 20:47
     * @Param [userFileId]
     * @return com.example.demo.entity.UserFile
     **/
    UserFile getById(long userFileId);

    /**
     * @Description 根据id数组查询
     * @Author wanxin
     * @Date 2019/11/3 20:49
     * @Param [Ids]
     * @return com.example.demo.entity.UserFile
     **/
    @Query(value = "from  UserFile where id in :Ids")
    List<UserFile> getByIds(@Param("Ids") long[] Ids);

}
