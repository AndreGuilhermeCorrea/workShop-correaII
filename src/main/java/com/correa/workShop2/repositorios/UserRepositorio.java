package com.correa.workShop2.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.correa.workShop2.dominio.User;

@Repository
public interface UserRepositorio extends MongoRepository<User, String> {

}
