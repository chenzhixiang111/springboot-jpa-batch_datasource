package com.example.batch_datasource.repository.testdb2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.batch_datasource.entity.db2.UserEntity2;

@Repository
public interface UserRepository2 extends JpaRepository<UserEntity2, Integer>{

}
