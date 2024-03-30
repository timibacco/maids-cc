package core.maidscc.serviceImpl;

import core.maidscc.dto.clientDTO;
import core.maidscc.dto.productDTO;
import core.maidscc.entity.ClientManagement;
import core.maidscc.entity.UserManagement;
import core.maidscc.exceptions.ClientAlreadyExistException;
import core.maidscc.exceptions.ClientNotFoundException;
import core.maidscc.repository.ClientRepository;
import core.maidscc.repository.UserRepository;
import core.maidscc.service.ClientService;
import core.maidscc.utils.Helper;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.beans.FeatureDescriptor;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    @Autowired
    private final ClientRepository clientrepo;

    @Autowired
    private final Helper helper;

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


    @Override
    public ClientManagement updateClient(Long id, clientDTO request) throws Exception {
        ClientManagement existingClient = clientrepo.findById(id).orElseThrow(ClientNotFoundException::new);



        String[] nulls  = helper.getNullPropertyNames(request);

        BeanUtils.copyProperties(request, existingClient, nulls);
        clientrepo.save(existingClient);

        return existingClient;



    }






}
