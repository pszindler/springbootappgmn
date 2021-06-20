package pl.pszindler.UEkat.Project.GNG.game;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            GameRepository repository) {
        return args -> {
            Game g1 = new Game();
            repository.save(g1);
        };
    }
}
