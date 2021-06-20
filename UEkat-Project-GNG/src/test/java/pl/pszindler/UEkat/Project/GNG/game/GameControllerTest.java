package pl.pszindler.UEkat.Project.GNG.game;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GameControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GameService gameService;

    @Test
    void getHiscores() throws Exception {
        GameService gs = Mockito.mock(GameService.class);
        GameController gc = new GameController(gs);
        RequestBuilder request = MockMvcRequestBuilders.get("/api/hiscores");
        MvcResult result = mvc.perform(request).andReturn();
    }

    @Test //
    void registerNewSession() throws Exception {
        Date date = new Date();
        Game g2 = new Game(100L, 0, 2, date, true );
        when(gameService.addNewSession(g2)).thenReturn(g2);
        this.mvc.perform(post("/api/start")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void guess() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/api/guess/1?guess=72"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}