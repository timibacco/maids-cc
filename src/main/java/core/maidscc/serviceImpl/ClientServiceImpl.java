package core.maidscc.serviceImpl;

import core.maidscc.dto.clientDTO;
import core.maidscc.entity.ClientManagement;
import core.maidscc.entity.UserManagement;
import core.maidscc.exceptions.ClientAlreadyExistException;
import core.maidscc.exceptions.ClientNotFoundException;
import core.maidscc.repository.ClientRepository;
import core.maidscc.repository.UserRepository;
import core.maidscc.service.ClientService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    @Autowired
    private final ClientRepository clientrepo;

//    @Autowired
//    private final UserRepository userrepo; // clients must have existing user accounts
    @Override
    public ClientManagement createClient(clientDTO request) {
        Optional<ClientManagement> existing = clientrepo.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            throw new ClientAlreadyExistException();
        }

        ClientManagement clientAccount = new ClientManagement();
        clientAccount.setEmail(request.getEmail());
        clientAccount.setLastName(request.getLastName());
        clientAccount.setPhoneNumber(request.getPhoneNumber());

        clientrepo.saveAndFlush(clientAccount);
        return clientAccount;

    }

    @Override
    public void deleteClient(@Nonnull Long id) {
        if (id < 0) {
            throw new NullPointerException("Invalid client id");
        }
        clientrepo.deleteById(id);
    }

    public ClientManagement updateClient(Long id, Map<String, Object> fields) {
        ClientManagement existingProduct = clientrepo.findById(id)
                .orElseThrow(ClientNotFoundException::new);

        if (existingProduct != null) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(ClientManagement.class, key);
                assert field != null;
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingProduct, value);
            });
            return clientrepo.save(existingProduct);
        }
        return null;

    }



}
