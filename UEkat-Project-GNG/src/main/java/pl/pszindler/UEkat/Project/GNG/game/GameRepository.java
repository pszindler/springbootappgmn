package pl.pszindler.UEkat.Project.GNG.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository
        extends JpaRepository<Game, Long> {

    @Query("SELECT s FROM Game s WHERE s.id = ?1")
    Optional<Game> findGameById(Long id);

    @Query(value = "SELECT * FROM Game u WHERE is_win = true ORDER BY attempts, best_time LIMIT 10", nativeQuery = true)
    public List<Game> best10Games();

}
