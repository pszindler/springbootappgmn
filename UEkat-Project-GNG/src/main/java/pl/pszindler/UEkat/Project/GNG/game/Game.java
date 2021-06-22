package pl.pszindler.UEkat.Project.GNG.game;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
/**
 * Author: Patryk Szindler
 * Klasa na potrzeby tworzenia obiekty gry.
 * Struktura servisu jest następujaca, Warstwa API komunikuje sie z servisem który ma dostęp do Interfejsu
 * repo, a ten z kolei odpowiada za komunikacje z bazą danych.
 */


@Entity
@Table
public class Game {
    @Id
    @SequenceGenerator(
            name = "game_sequence",
            sequenceName = "game_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "game_sequence"
    )
    private Long id;
    private Integer number;
    private Integer attempts = 0;
    private Date sessionStart;
    private Date sessionEnd;
    private Long bestTime = 0L;
    private Boolean isWin = false;
    private String message = "";

    public Game() {
        this.number = generateRandomNumber();
        this.attempts = attempts;
        this.sessionStart = getSessionStart();
        this.sessionEnd = getSessionStart();
        this.isWin = isWin;
    }

    public Game(Long id, Integer number, Integer attempts, Date sessionStart, Boolean isWin) {
        this.id = id;
        this.number = number;
        this.attempts = attempts;
        this.sessionStart = sessionStart;
        this.isWin = isWin;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", number=" + number +
                ", attempts=" + attempts +
                ", sessionStart=" + sessionStart +
                ", isWin=" + isWin +
                ", message='" + message + '\'' +
                ", sessionEnd=" + sessionEnd +
                ", bestTime=" + bestTime +
                '}';
    }

    public int generateRandomNumber() {
        Random r = new Random();
        int randomInt = r.nextInt(100) + 1;
        return randomInt;
    };

    public Date getSessionStart() {
        Date d = new Date();
        return d;
    }

    public Integer calculateAttempts() {

        return this.attempts++;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public void setSessionStart(Date sessionStart) {
        this.sessionStart = sessionStart;
    }

    public Boolean getEnd() {
        return isWin;
    }

    public void setEnd(Boolean isWin) {
        this.isWin = isWin;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSessionEnd() {
        return sessionEnd;
    }

    public void setSessionEnd(Date sessionEnd) {
        this.sessionEnd = sessionEnd;
    }

    public Long getBestTime() {
        return this.bestTime;
    }

    public Long setBestTime() {
        Long time = TimeUnit.SECONDS.convert(this.sessionEnd.getTime() -
                this.sessionStart.getTime(), TimeUnit.MILLISECONDS);
        return this.bestTime = time;
    }

    public void setSessionWin() {
        Date date = new Date();
        this.sessionEnd = date;
        Long time = TimeUnit.SECONDS.convert(this.sessionEnd.getTime() -
                this.sessionStart.getTime(), TimeUnit.MILLISECONDS);
        this.bestTime = time;
        this.isWin = true;
    }
}
