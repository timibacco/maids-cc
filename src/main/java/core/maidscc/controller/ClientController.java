package core.maidscc.controller;

import core.maidscc.entity.ClientManagement;
import core.maidscc.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import core.maidscc.dto.clientDTO;

import java.util.Map;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    @Autowired
    private final ClientService clientService;


    /**
     * This endpoint creates new clients
     */

    @PostMapping("/create")
    public ResponseEntity<ClientManagement> createClient(@RequestBody clientDTO request){
        return ResponseEntity.ok(clientService.createClient(request));
    }


    /**
     * This endpoint deletes clients
     */
    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
    }


    /**
     * This endpoint updates clients
     */
    @RequestMapping("/update/{id}")
    public ResponseEntity<ClientManagement> updateClient(@PathVariable Long id, @RequestBody clientDTO request) throws Exception{
        return ResponseEntity.ok(clientService.updateClient(id, request));
    }
}
