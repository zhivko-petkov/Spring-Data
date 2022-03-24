package com.example.xmlexercise.repository;

import com.example.xmlexercise.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM users u " +
            "WHERE u.soldProducts.size > 0 " +
            "ORDER BY u.lastName, u.firstName")
    List<User> findUsersSuccessfullySoldProducts();

    @Query("SELECT u FROM users u" +
            " WHERE u.soldProducts.size > 0" +
            " ORDER BY u.soldProducts.size DESC, u.lastName")
    List<User> findUsersHasSoldProduct();

}
