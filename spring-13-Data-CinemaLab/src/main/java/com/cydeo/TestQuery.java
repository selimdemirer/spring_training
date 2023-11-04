package com.cydeo;

import com.cydeo.enums.MovieState;
import com.cydeo.enums.MovieType;
import com.cydeo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.cydeo.enums.MovieState.ACTIVE;
import static com.cydeo.enums.MovieType.REGULAR;

@Component
public class TestQuery implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final CinemaRepository cinemaRepository;
    private final GenreRepository genreRepository;
    private final MovieCinemaRepository movieCinemaRepository;
    private final MovieRepository movieRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TestQuery(AccountRepository accountRepository, CinemaRepository cinemaRepository, GenreRepository genreRepository, MovieCinemaRepository movieCinemaRepository, MovieRepository movieRepository, TicketRepository ticketRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.cinemaRepository = cinemaRepository;
        this.genreRepository = genreRepository;
        this.movieCinemaRepository = movieCinemaRepository;
        this.movieRepository = movieRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("//AccountRepository");
        System.out.println(accountRepository.findAllByAgeBetween(25,35));
        System.out.println(accountRepository.findByOrderByAgeDesc());
        System.out.println(accountRepository.sortAccountsByAgeDesc());
        System.out.println(accountRepository.retrieveAccountsByAgeLowerThan(40));
        System.out.println(accountRepository.retrieveBySearchCriteria("Josie"));

        System.out.println("//CinemaRepository");
        System.out.println(cinemaRepository.fetchById(5L));
        System.out.println(cinemaRepository.retrieveAllByNameOrSponsoredName("Quentin"));

        System.out.println("//GenreRepository");
        System.out.println(genreRepository.retrieveByName("Josie"));

        System.out.println("//MovieCinemaRepository");
        System.out.println(movieCinemaRepository.findFirst3ByOrderByMoviePriceDesc());
        System.out.println(movieCinemaRepository.retrieveAllByLocationName("Awl"));

        System.out.println("//MovieRepository");
        System.out.println(movieRepository.retrieveByName("Josie"));

        System.out.println("//TicketRepository");
        System.out.println(ticketRepository.countAllTicketsByUserAccount(4L));

        System.out.println("//UserRepository");
        System.out.println(userRepository.findAllByAccount_AgeGreaterThan(25));

    }

}
