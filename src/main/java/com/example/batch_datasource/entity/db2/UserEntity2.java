package com.example.batch_datasource.entity.db2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "t_user2")
public class UserEntity2 {

	@Id
	@GeneratedValue//表示自增
	private Integer id;
	@Column
	private String name;
	@Column
	private Integer age;
	
	public UserEntity2(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
}
