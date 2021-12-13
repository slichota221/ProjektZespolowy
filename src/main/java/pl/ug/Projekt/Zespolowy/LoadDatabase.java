package pl.ug.Projekt.Zespolowy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.ug.Projekt.Zespolowy.domain.*;
import pl.ug.Projekt.Zespolowy.repository.GameRepository;
import pl.ug.Projekt.Zespolowy.repository.GenreRepository;
import pl.ug.Projekt.Zespolowy.repository.PublisherRepository;
import pl.ug.Projekt.Zespolowy.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(GenreRepository repository){
        return args ->{
            log.info("Preloading " + repository.save(new Genre("RPG")));
            log.info("Preloading " + repository.save(new Genre("Action")));

        };
    }

    @Bean
    CommandLineRunner initAdmin(UserRepository repository){
        return args ->{
            log.info("Preloading " + repository.save(new User("admin", new BCryptPasswordEncoder().encode("admin"), "admin", LocalDateTime.now(), Role.ADMIN)));

        };
    }

    @Bean
    CommandLineRunner addPublisher(PublisherRepository publisherRepository){
        return args ->{
            log.info("Preloading " + publisherRepository.save(new Publisher("Konami")));
        };
    }
    @Bean
    CommandLineRunner addGame(GameRepository gameRepository){
        return args ->{
            log.info("Preloading" + gameRepository.save(new Game("Witcher", LocalDate.of(2007, 10, 26),
                    "You kill monsters", "/covers/cover_witcher1.jpg", new Genre("RPG"), new Publisher("CDProjekt Red"))));
            log.info("Preloading" + gameRepository.save(new Game("Witcher 3", LocalDate.of(2015, 5, 19),
                    "You kill more monsters", "/covers/cover_witcher3.jpg", new Genre("RPG"), new Publisher("CDProjekt"))));
        };
    }
    /*
        @Bean
    CommandLineRunner initDatabaseGame(GameRepository gameRepository){
        return args ->{
            // String nameGame, String dateRelease, String description, String linkCover, int idGenre, int idPublisher
            log.info("Preloading " + gameRepository.save(new Game("Witcher", "26.10.2007" ,"You kill monsters", "", 1,1 )));
            log.info("Preloading " + gameRepository.save(new Game("Witcher 2: Assassins of Kings", "10.05.2011" ,"You kill more monsters", "", 1,1 )));
            log.info("Preloading " + gameRepository.save(new Game("Witcher 3: Wild Hunt", "19.05.2015" ,"You kill even more monsters", "", 1,1 )));
            log.info("Preloading " + gameRepository.save(new Game("Doom", "12.05.2016" ,"You kill demons", "", 2,2 )));
            log.info("Preloading " + gameRepository.save(new Game("Doom Eternal", "20.04.2020" ,"You kill more demons", "", 2,2 )));
            log.info("Preloading " + gameRepository.save(new Game("The Elder Scrolls V: Skyrim", "11.11.2011" ,"You kill dragons", "", 1,2 )));
        };
    }
     */

}