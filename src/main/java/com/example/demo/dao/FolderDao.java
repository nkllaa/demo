package com.example.demo.dao;

import com.example.demo.entity.Folder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FolderDao extends JpaRepository<Folder,Long>, JpaSpecificationExecutor {
    public Folder getById(long id);

    /**
     * @Description 查询用户发根目录
     * @Author wanxin
     * @Date 2019/8/22 14:38
     * @Param [userId]
     * @return com.example.demo.entity.Folder
     **/
    @Query(value = "from Folder where user.id=:userId and folderAndFileBase is null ")
    Folder getUserFolderAndFileRoot(@Param("userId") long userId);

    /**
     * @Description 根据用户id和父级目录id查询
     * @Author wanxin
     * @Date 2019/8/22 14:38
     * @Param [folderId, userId]
     * @return java.util.List<com.example.demo.entity.Folder>
     **/
    @Query(value = "from  Folder where folderAndFileBase.id=:folderId and user.id=:userId")
    List<Folder> getByFolderIdAndUserId(@Param("folderId") long folderId, @Param("userId") long userId);
    /**
     * @Description TODO
     * @Author wanxin
     * @Date 2019/10/30 20:22
     * @Param [name, folderId]
     * @return com.example.demo.entity.Folder
     **/
    @Query(value = "from  Folder where folderAndFileBase.id=:folderId and name=:name")
    Folder getByNameAndFolder(@Param("name") String name,@Param("folderId") long folderId);


    @Query(value = "select f from Folder f where folderAndFileBase.id=:folderId and f.name like concat('%',:condition,'%')  order by  f.createDate desc")
    Page<Folder>findByFolderPage(String condition,@Param("folderId") long folderId, Pageable pageable);
}
