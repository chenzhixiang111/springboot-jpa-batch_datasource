package com.example.batch_datasource.entity.db1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "t_user1")
public class UserEntity1 {

	@Id
	@GeneratedValue//表示自增
	private Integer id;
	@Column
	private String name;
	@Column
	private Integer age;
	
	public UserEntity1(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
}
