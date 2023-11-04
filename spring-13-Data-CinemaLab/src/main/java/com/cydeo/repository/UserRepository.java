package com.cydeo.repository;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to read a user with an email?
    Optional<User> findByEmail(String email);

    //Write a derived query to read a user with a username?
    Optional<User> findByUsername(String username);

    //Write a derived query to list all users that contain a specific name?
    List<User> findAllByAccount_NameContaining(String name);

    //Write a derived query to list all users that contain a specific name in the ignore case mode?
    List<User> findAllByAccount_NameContainingIgnoreCase(String name);

    //Write a derived query to list all users with an age greater than a specified age?
    List<User> findAllByAccount_AgeGreaterThan(Integer age);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query that returns a user read by email?
    @Query("SELECT u FROM User u WHERE u.email = :userEmail")
    Optional<User> fetchByEmail(@Param("userEmail") String userEmail);

    //Write a JPQL query that returns a user read by username?
    @Query("SELECT u FROM User u WHERE u.username = :userUsername")
    Optional<User> fetchByUsername(@Param("userUsername") String userUsername);

    //Write a JPQL query that returns all users?
    @Query("SELECT u FROM User u")
    List<User> fetchAllUsers();

    // ------------------- Native QUERIES ------------------- //

    //Write a native query that returns all users that contain a specific name?
    @Query(nativeQuery = true, value = "SELECT * FROM user_account ua" +
            " JOIN account_details ad ON ad.id = ua.account_details_id" +
            " WHERE ad.name ILIKE concat('%', ?1, '%')")
    List<User> retrieveAllByName(String name);

    //Write a native query that returns all users?
    @Query(nativeQuery = true, value = "SELECT * FROM user_account")
    List<User> retrieveAll();

    //Write a native query that returns all users in the range of ages?
    @Query(nativeQuery = true, value = "SELECT * FROM user_account ua" +
            " JOIN account_details ad ON ad.id = ua.account_details_id" +
            " WHERE ad.age BETWEEN ?1 AND ?2")
    List<User> retrieveUsersBetweenAgeRange(Integer age1, Integer age2);

    //Write a native query to read a user by email?
    @Query(nativeQuery = true, value = "SELECT * FROM user_account WHERE email = :userEmail")
    User retrieveByEmail(@Param("userEmail") String userEmail);

}