package pl.pszindler.UEkat.Project.GNG.game;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/***
 * Klasa zawiera warstwy API
 * 1 request GET do pobierania hiscores = 10 najlepszych wynikow
 * 1 request GET do zgadywania liczby w danej sesji - api/guess/2?guess=20 (liczba 20 dla sesji 2)
 * 1 request POST do utworzenia w bazie nowej gry. Przy starcie serwisu tworzy sie juz jeden rekord a więc
 * POST stworzy gre z id = 2.
 */


@RestController
@RequestMapping(path = "api")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(path = "/hiscores") ///
    public List<Map<String, String>> getHiscores(){
        return gameService.getHighscores();
    }

    @PostMapping(path = "/start") ///
    public Long registerNewSession(Game game) {
        gameService.addNewSession(game);
        return game.getId();
    }

    @GetMapping(path = "/guess/{id}") ///
    public Map<String, String> guess(@PathVariable("id") long id, Integer guess) {
        return gameService.guess(id, guess);
    }

    @GetMapping(path = "/test")
    public ResponseEntity<Test> test() {
        Test t1 = new Test(1, "text", 2);
        return ResponseEntity.notFound().build();
    }
}
