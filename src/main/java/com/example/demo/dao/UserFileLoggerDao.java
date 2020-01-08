package com.example.demo.dao;

import com.example.demo.entity.UserFileLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserFileLoggerDao extends JpaRepository<UserFileLogger,Long>, JpaSpecificationExecutor<UserFileLogger> {
    @Query(value = "from UserFileLogger where user.id=:userId and isUpload=0")
    List<UserFileLogger> getByUserIdUp(long userId);

    @Query(value = "from UserFileLogger where user.id=:userId and isDelete=0")
    List<UserFileLogger> getByUserIdDe(long userId);

    List<UserFileLogger> getByUserFileId(long fileId);
}
