package com.bnymellon.Vc.repository;

import org.springframework.data.repository.CrudRepository;

import com.bnymellon.Vc.model.User;

public interface VcRepository extends CrudRepository<User,String>{

}
