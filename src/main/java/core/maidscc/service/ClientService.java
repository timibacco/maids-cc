package core.maidscc.service;


import core.maidscc.dto.clientDTO;
import core.maidscc.entity.ClientManagement;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface ClientService {

    ClientManagement createClient(clientDTO request);

    void deleteClient(Long id);
    public ClientManagement updateClient(Long id, Map<String, Object> fields);
}
