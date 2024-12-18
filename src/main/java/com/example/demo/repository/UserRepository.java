package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    List<User> findByIdIn(List<Long> userIds);

    default User findByIdOrElseThrow(Long id) {
        return findUserById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 ID에 맞는 값이 존재하지 않습니다."));
    }

    Optional<User> findUserById(Long id);

    @Modifying
    @Query("Update User u Set u.status = :blocked WHERE u IN :userIds")
    void reportUsers(String blocked, List<User> userIds);
}
