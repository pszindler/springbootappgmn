package pl.pszindler.UEkat.Project.GNG.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game getGameById(Long id) {
        return gameRepository.getById(id);
    }

    public List<Map<String, String>> getHighscores() {
        List<Map<String, String>> topScores = new ArrayList<>();
        List<Game> gameList = gameRepository.best10Games();
        HashMap<String, String> map = new HashMap<>();
        for (Game g : gameList) {
            map.put("best_time", g.getBestTime() +"s");
            map.put("attempts", g.getAttempts()+"");
            map.put("id", g.getId()+"");
            topScores.add(map);
            map = new HashMap<>();
        }
        return topScores;
    }

    public Game addNewSession(Game game) {
        Optional<Game> gameByID = gameRepository.findGameById(game.getId());
        if (gameByID.isPresent()) {
            throw new IllegalStateException("Session id already taken!");
        }
        gameRepository.save(game);
        return game;
    }

    public void save(Game game) {
        gameRepository.save(game);
    }

    public Map<String, String> guess(long id, Integer guess) {
        HashMap<String, String> map = new HashMap<>();
        Game game = getGameById(id);
        map.put("id",game.getId()+"");
        if (game.getEnd()) {
            map.put("attempts", game.getAttempts()+"");
            game.setMessage("Chill, you already won :)");
            map.put("message", game.getMessage());
            save(game);
            return map;
        }
        if (guess == game.getNumber()) {
            game.calculateAttempts();
            game.setSessionWin();
            map.put("attempts", game.getAttempts()+"");
            game.setMessage("You win :)");
            map.put("message", game.getMessage());
            save(game);
            return map;
        }
        else if (guess > game.getNumber()) {
            game.setMessage("Number is to high");
            game.calculateAttempts();
            map.put("attempts", game.getAttempts()+"");
            map.put("message", game.getMessage());
            save(game);
            return map;
        } else if (guess < game.getNumber()) {
            game.setMessage("Number is to low");
            game.calculateAttempts();
            map.put("attempts", game.getAttempts()+"");
            map.put("message", game.getMessage());
            save(game);
            return map;
        }
        return map;
    }
}
