package pl.pszindler.UEkat.Project.GNG.game;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Transient;
import javax.transaction.Transactional;

public class Test {
    @JsonIgnore
    private Integer id;
    private String message;
    private Integer attempts;

    public Test(Integer id, String message, Integer attempts) {
        this.id = id;
        this.message = message;
        this.attempts = attempts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }
}
