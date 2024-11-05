package com.example.Instargram.user.repository;

import com.example.Instargram.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>, UserRepository{
    @Query(value = "SELECT * from user where email= :email",nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);
    //반드시 없는 메서드는 raw 쿼리,JPQL,Querydsl 써서 따로 구현하기
    //Optional 객체로 던지면 존재하냐 존재하지 않냐에 따라 bool로 쓸수 있다.
}
