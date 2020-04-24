package ro.mxp.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.mxp.food.dto.ClientDto;
import ro.mxp.food.service.ClientService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDto> getAllClient() {
        return clientService.getAllClient();
    }

    @PostMapping
    public void addClient(@RequestBody ClientDto clientDto) {
        clientService.addClient(clientDto);
    }

    @PutMapping("/{id}")
    public void updateClient(@PathVariable Long id, @RequestParam String email, @RequestParam String username, @RequestParam String phoneNumber,
                             @RequestParam String firstName, @RequestParam String lastName, @RequestParam Date dateOfBirth) {
        clientService.updateClient(id, email, username, phoneNumber, firstName, lastName, dateOfBirth);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

}
