package com.hl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hl.model.MUser;

public interface UserRepository extends JpaRepository<MUser, Long> {

    MUser findByName(String name);

    MUser findByNameAndAge(String name, Integer age);

    /*@Query("from MUser u where u.name=:name")
    MUser findUser(@Param("name") String name);*/

}