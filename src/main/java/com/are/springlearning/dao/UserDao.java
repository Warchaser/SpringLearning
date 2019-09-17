package com.are.springlearning.dao;

import com.are.springlearning.bean.TUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserDao extends JpaRepository<TUserEntity, Integer> {

    @Modifying
    @Transactional

    @Query("update TUserEntity us set us.name=:qName, us.age=:qAge, us.gender=:qGender where us.id=:qId")
    void updateUser(
            @Param("qName") String name,
            @Param("qAge") Integer age,
            @Param("qGender") Integer gender,
            @Param("qId") Integer id
    );

}
