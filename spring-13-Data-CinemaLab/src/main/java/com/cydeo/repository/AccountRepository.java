package com.cydeo.repository;

import com.cydeo.entity.Account;
import com.cydeo.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to list all accounts with a specific country or state
    List<Account> findAllByCountryOrState(String country, String state);

    //Write a derived query to list all accounts with age lower than or equal to a specific value
    List<Account> findAllByAgeLessThanEqual(Integer age);

    //Write a derived query to list all accounts with a specific role
    List<Account> findAllByRole(UserRole role);

    //Write a derived query to list all accounts between a range of ages
    List<Account> findAllByAgeBetween(Integer age1, Integer age2); // Beetween is "Inclusive"

    //Write a derived query to list all accounts where the beginning of the address contains the keyword
    List<Account> findByAddressStartingWith(String keyword);

    //Write a derived query to sort the list of accounts with age
    List<Account> findByOrderByAgeDesc();

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query that returns all accounts
    @Query("select e from Account e")
    List<Account> fetchAllAccounts();

    //Write a JPQL query to list all admin accounts
    @Query("select e from Account e where e.role = 'ADMIN'")
    List<Account> fetchAdminAccounts();

    //Write a JPQL query to sort all accounts with age
    @Query("select e from Account e order by e.age desc")
    List<Account> sortAccountsByAgeDesc();

    // ------------------- Native QUERIES ------------------- //

    //Write a native query to read all accounts with an age lower than a specific value
    @Query(value = "select * from account_details where age < ?1", nativeQuery = true)
    List<Account> retrieveAccountsByAgeLowerThan(Integer age); // Can be used with @Param("age) too

    //Write a native query to read all accounts that a specific value can be containable in the name, address, country, state, city
    @Query(value = "SELECT * FROM account_details ad WHERE name ILIKE concat('%', ?1, '%') " +
            "OR country ILIKE concat('%', ?1, '%') " +
            "OR address ILIKE concat('%', ?1, '%') " +
            "OR ad.state ILIKE concat('%', ?1, '%') " + // "state" is a reserved keyword, so we added "ad"
            "OR city ILIKE concat('%', ?1, '%')", nativeQuery = true)
    List<Account> retrieveBySearchCriteria(@Param("pattern") String pattern);

//    @Query(nativeQuery = true, value = "SELECT * FROM account_details ad WHERE name ILIKE concat('%', :value, '%')" +
//            " OR address ILIKE concat('%', :value, '%')" +
//            " OR country ILIKE concat('%', :value, '%')" +
//            " OR ad.state ILIKE concat('%', :value, '%')" +
//            " OR city ILIKE concat('%', :value, '%')")
//    List<Account> retrieveBySearchCriteria(@Param("value") String value);

    //Write a native query to read all accounts with an age higher than a specific value
    @Query(value = "select * from account_details where age > :age", nativeQuery = true)
    List<Account> retrieveAccountsByAgeHigherThan(@Param("age") Integer age); // Can be used with ?1 too

}