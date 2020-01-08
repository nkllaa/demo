package com.example.demo.dao;

import com.example.demo.entity.FolderLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FolderLoggerDao extends JpaRepository<FolderLogger,Long>, JpaSpecificationExecutor<FolderLogger> {
}
