
package core.maidscc;

import core.maidscc.dto.clientDTO;
import core.maidscc.entity.ClientManagement;
import core.maidscc.exceptions.ClientAlreadyExistException;
import core.maidscc.repository.ClientRepository;
import core.maidscc.service.ClientService;
import core.maidscc.serviceImpl.ClientServiceImpl;

import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class MaidsCcApplicationTests {







    @Mock
    private ClientRepository clientrepo;

    @Mock
    private ClientService clientService;



    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }





    @Test
    public void test_delete_invalid_id() {


        Long id = -1L;
        ClientService clientService = new ClientServiceImpl(clientrepo);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            clientService.deleteClient(id);
        });

        String expectedMessage = "Invalid client id";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void test_createClient_existingEmail() {
        clientDTO request = new clientDTO();
        request.setEmail("test@example.com");
        request.setLastName("Doe");
        request.setPhoneNumber("1234567890");

        ClientManagement existingClient = new ClientManagement();
        existingClient.setEmail("test@example.com");
        existingClient.setLastName("Smith");
        existingClient.setPhoneNumber("0987654321");

        when(clientrepo.findByEmail("test@example.com")).thenReturn(Optional.of(existingClient));

        ClientService clientService = new ClientServiceImpl(clientrepo);

        assertThrows(ClientAlreadyExistException.class, () -> clientService.createClient(request));
        verify(clientrepo, times(1)).findByEmail("test@example.com");
        verify(clientrepo, never()).saveAndFlush(any(ClientManagement.class));
    }

}