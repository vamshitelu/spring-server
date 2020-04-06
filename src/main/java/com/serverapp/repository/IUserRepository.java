package com.serverapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serverapp.entity.UserEntity;

@Repository
public interface IUserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findByUserName(String userName);
	
	UserEntity findByUserNameAndPassword(String userName, String password);
}
