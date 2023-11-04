package com.cydeo.repository;

import com.cydeo.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to count how many tickets a user bought
    Integer countAllByUserAccount_Id(Long userId);

    //Write a derived query to list all tickets by specific email
    List<Ticket> findAllByUserAccount_Email(String email);

    //Write a derived query to count how many tickets are sold for a specific movie
    Integer countAllByMovieCinema_Movie_Name(String name);

    //Write a derived query to list all tickets between a range of dates
    List<Ticket> findAllByDateTimeBetween(LocalDateTime dateTime1, LocalDateTime dateTime2);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query that returns all tickets are bought from a specific user
    @Query("SELECT t FROM Ticket t WHERE t.userAccount.id = ?1")
    List<Ticket> fetchAllTicketByUserAccountId(Long userId);

    //Write a JPQL query that returns all tickets between a range of dates
    @Query("SELECT t FROM Ticket t WHERE t.dateTime BETWEEN ?1 AND ?2")
    List<Ticket> fetchAllByDateTimeBetween(LocalDateTime dateTime1, LocalDateTime dateTime2);

    // ------------------- Native QUERIES ------------------- //

    //Write a native query to count the number of tickets a user bought
    @Query(nativeQuery = true, value = "SELECT count(*) FROM ticket WHERE user_account_id = :userId")
    Integer countAllTicketsByUserAccount(@Param("userId") Long userId);

    //Write a native query to count the number of tickets a user bought in a specific range of dates
    @Query(nativeQuery = true, value = "SELECT count(*) FROM ticket WHERE user_account_id = ?1 AND dateTime BETWEEN ?2 AND ?3")
    Integer countAllByDateTimeBetween(Long userId, LocalDateTime dateTime1, LocalDateTime dateTime2);

    //Write a native query to distinct all tickets by movie name
    @Query(nativeQuery = true, value = "SELECT DISTINCT m.name FROM ticket t JOIN movie_cinema mc ON mc.id = t.movie_cinema_id" +
            " JOIN movie m ON m.id = mc.movie_id")
    List<String> retrieveAllDistinctMovieNames();

    //Write a native query to find all tickets by user email
    @Query(nativeQuery = true, value = "SELECT * FROM ticket t JOIN user_account ua ON t.user_account_id = ua.id" +
            " WHERE ua.email = :userEmail")
    List<Ticket> findAllByUserEmail(@Param("userEmail") String userEmail);

    //Write a native query that returns all tickets
    @Query(nativeQuery = true, value = "SELECT * FROM ticket")
    List<Ticket> retrieveAll();

    //Write a native query to list all tickets where a specific value should be containable in the username or
    //account name or movie name
    @Query(nativeQuery = true, value = "SELECT * FROM ticket t JOIN user_account ua ON t.user_account_id = ua.id" +
            " JOIN account_details ad ON ad.id = ua.account_details_id" +
            " JOIN movie_cinema mc ON mc.id = t.movie_cinema_id" +
            " JOIN movie m ON mc.movie_id = m.id" +
            " WHERE ua.username ILIKE concat('%', ?1, '%')" +
            " OR ad.name ILIKE concat('%', ?1, '%')" +
            " OR m.name ILIKE concat('%', ?1, '%')")
    List<Ticket> retrieveAllBySearchCriteria(String searchCriteria);

}