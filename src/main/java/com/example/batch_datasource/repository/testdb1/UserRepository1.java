package com.example.batch_datasource.repository.testdb1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.batch_datasource.entity.db1.UserEntity1;
@Repository
public interface UserRepository1 extends JpaRepository<UserEntity1, Integer>{

}
