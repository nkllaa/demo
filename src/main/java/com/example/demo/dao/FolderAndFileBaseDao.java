package com.example.demo.dao;

import com.example.demo.entity.FolderAndFileBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FolderAndFileBaseDao extends JpaRepository<FolderAndFileBase,Long>, JpaSpecificationExecutor {

    /**
     * @Description 查询对应文件夹下面的文件或文件夹
     * @Author wanxin
     * @Date 2019/10/29 16:35
     * @Param [folderAndFileBaseById]
     * @return java.util.List<com.example.demo.entity.FolderAndFileBase>
     **/
    @Query(value = "from FolderAndFileBase p where folderAndFileBase.id=:folderAndFileBaseId")
    List<FolderAndFileBase> getByFolderAndFileBaseId(@Param("folderAndFileBaseId")long folderAndFileBaseId);
    /**
     * @Description 进入指定文件夹并分页
     * @Author wanxin
     * @Date 2019/11/1 16:41
     * @Param [condition, folderId, pageable]
     * @return org.springframework.data.domain.Page<com.example.demo.entity.FolderAndFileBase>
     **/
    @Query(value = "from FolderAndFileBase f  where f.folderAndFileBase.id=:folderId and f.name like concat('%',:condition,'%') order by  f.createDate desc")
    Page<FolderAndFileBase> findByFolderPage(@Param("condition")String condition, @Param("folderId") long folderId, Pageable pageable);
    /**
     * @Description 根据id查询
     * @Author wanxin
     * @Date 2019/11/4 20:01
     * @Param [folderAndFileBase]
     * @return com.example.demo.entity.FolderAndFileBase
     **/
    FolderAndFileBase getById(long folderAndFileBase);
}
