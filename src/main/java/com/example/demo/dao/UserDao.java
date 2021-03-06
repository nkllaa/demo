package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<User,Long>, JpaSpecificationExecutor {
    public User findByUserName(String userName);
    public User findByPhoneNumber(String phoneNumber);
    public User getById(long userId);
}
