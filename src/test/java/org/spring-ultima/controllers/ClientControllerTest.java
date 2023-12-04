import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.ultimacrm.controller.ClientController;
import org.ultimacrm.dtos.ClientInputDTO;
import org.ultimacrm.exceptions.ExceptionHandler;
import org.ultimacrm.models.Client;
import org.ultimacrm.service.ClientService;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerTest {

    //"mockando" clientService
    @Mock
    private ClientService clientService;

    //"mokar" criar um client fictício para testar
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new ClientController(clientService))
                .setControllerAdvice(new ExceptionHandler())
                .build();
    }

    @Test
    public void getByValidIdShouldReturnSuccess200ok() throws Exception {
        //arrange
        String path = "/client/{clientId}";
        Integer clientId = 10;
        String fakeValue = "fakeValue";

        Client expectedClient = new Client(clientId, fakeValue, fakeValue, fakeValue);

        Mockito.when(clientService.getClientById(clientId))
                .thenReturn(expectedClient);

        //act - assert
        this.mockMvc.perform(
                        get(path, clientId)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(clientId));
    }

    @Test
    public void addClientInvalidValuesShouldReturn400BadRequest() throws Exception {
        String path = "/client";
        String fakeValue = "fakeValue";

        ClientInputDTO inputDTO = new ClientInputDTO(fakeValue, fakeValue, fakeValue);

        this.mockMvc.perform(
                post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(inputDTO))
        )
                .andExpect(status().isBadRequest());
    }

}