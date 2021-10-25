package pl.ug.Projekt.Zespolowy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.ug.Projekt.Zespolowy.domain.Genre;
import pl.ug.Projekt.Zespolowy.repository.GenreRepository;

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

}