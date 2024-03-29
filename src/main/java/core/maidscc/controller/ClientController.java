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



    @PostMapping("/create")
    public ResponseEntity<ClientManagement> createClient(@RequestBody clientDTO request){
        return ResponseEntity.ok(clientService.createClient(request));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable("id") Long id){
        clientService.deleteClient(id);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ClientManagement> updateClient(@PathVariable("id") Long id, @RequestBody Map<String, Object> request){
        return ResponseEntity.ok(clientService.updateClient(id, request));
    }
}
