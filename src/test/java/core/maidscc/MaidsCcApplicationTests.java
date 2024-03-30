
package core.maidscc;

import core.maidscc.dto.clientDTO;
import core.maidscc.entity.ClientManagement;
import core.maidscc.exceptions.ClientAlreadyExistException;
import core.maidscc.exceptions.ClientNotFoundException;
import core.maidscc.repository.ClientRepository;
import core.maidscc.service.ClientService;
import core.maidscc.serviceImpl.ClientServiceImpl;

import core.maidscc.utils.Helper;
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
    private Helper helper;





    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }





    @Test
    public void test_delete_invalid_id() {


        Long id = -1L;
        ClientService clientService =   new ClientServiceImpl(clientrepo, helper);

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

        ClientService clientService = new ClientServiceImpl(clientrepo, helper);

        assertThrows(ClientAlreadyExistException.class, () -> clientService.createClient(request));
        verify(clientrepo, times(1)).findByEmail("test@example.com");
        verify(clientrepo, never()).saveAndFlush(any(ClientManagement.class));
    }

    @Test
    public void test_updateClient_validInput() throws Exception {
        Long id = 1L;
        clientDTO request = new clientDTO();
        request.setEmail("test@example.com");
        request.setLastName("Doe");
        request.setPhoneNumber("1234567890");

        ClientManagement existingClient = new ClientManagement();
        existingClient.setId(id);
        existingClient.setEmail("old@example.com");
        existingClient.setLastName("Smith");
        existingClient.setPhoneNumber("9876543210");

        ClientManagement updatedClient = new ClientManagement();
        updatedClient.setId(id);
        updatedClient.setEmail(request.getEmail());
        updatedClient.setLastName(request.getLastName());
        updatedClient.setPhoneNumber(request.getPhoneNumber());

        ClientService clientService = new ClientServiceImpl(clientrepo, helper);

        when(clientrepo.findById(id)).thenReturn(Optional.of(existingClient));
        when(clientService.updateClient(id, request)).thenReturn(updatedClient);

        ClientManagement result = clientService.updateClient(id, request);

        assertEquals(request.getEmail(), result.getEmail());
        assertEquals(request.getLastName(), result.getLastName());
        assertEquals(request.getPhoneNumber(), result.getPhoneNumber());
    }
    @Test
    public void test_updateClient_invalidId() {
        Long id = -1L;
        clientDTO request = new clientDTO();
        request.setEmail("test@example.com");
        request.setLastName("Doe");
        request.setPhoneNumber("1234567890");

        ClientService clientService = new ClientServiceImpl(clientrepo, helper);
        when(clientrepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> {
            clientService.updateClient(id, request);
        });
    }

}