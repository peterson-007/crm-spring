import org.mockito.ArgumentMatchers;
import org.ultimacrm.dtos.ClientInputDTO;
import org.ultimacrm.models.Client;
import org.ultimacrm.repositories.ClientRepository;
import org.ultimacrm.service.ClientService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doReturn;

public class ClientServiceTest {

    private ClientService clientService;
    private ClientRepository clientRepository;

    @BerforeAll
    public void addClientCreateClientSuccess(){
        //Arrange
        String fakeValueClient = "test";
        Client client = new Client();
        client.setId(1);
        client.setCpf(fakeValueClient);
        client.setEmail(fakeValueClient);
        ClientInputDTO clientInputDTO = new ClientInputDTO();
        clientInputDTO.setCpf(fakeValueClient);
        clientInputDTO.setEmail(fakeValueClient);
        clientInputDTO.setName(fakeValueClient);
        doReturn(client).when(clientRepository).save(ArgumentMatchers.any());

        //Act
        Client retornoClient = clientService.addClient(clientInputDTO);

        //Assert
        assertEquals(fakeValueClient, retornoClient.getCpf());
        assertEquals(fakeValueClient, retornoClient.getName());
        assertEquals(fakeValueClient, retornoClient.getEmail());
    }
}