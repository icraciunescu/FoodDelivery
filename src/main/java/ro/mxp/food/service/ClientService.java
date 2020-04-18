package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.ClientDto;
import ro.mxp.food.entity.Client;
import ro.mxp.food.repository.ClientRepository;
import ro.mxp.food.utils.CurrentUsername;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private BCryptPasswordEncoder getBCryptPasswordEncoder;

    @Autowired
    private CurrentUsername currentUsername;

    private ModelMapper modelMapper = new ModelMapper();

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDto> getAllClient() {
        return clientRepository.findAll()
                .stream()
                .map(client -> modelMapper.map(client, ClientDto.class))
                .collect(Collectors.toList());
    }

    public void addClient(ClientDto clientDto) {
        clientDto.setPassword(getBCryptPasswordEncoder.encode(clientDto.getPassword()));
        clientRepository.save(modelMapper.map(clientDto, Client.class));
    }

    public void updateClient(Long id, String email, String username, String phoneNumber, String firstName, String lastName, Date dateOfBirth) {
        Client client = clientRepository.findByUsername(currentUsername.displayCurrentUsername());
        if (client != null && client.getId().equals(id)) {
            clientRepository.updateClientRepo(id, email, username, phoneNumber, firstName, lastName, dateOfBirth);
        }
    }

    public void deleteClient(Long id) {
        Client client = clientRepository.findByUsername(currentUsername.displayCurrentUsername());
        if (client != null && client.getId().equals(id)) {
            clientRepository.deleteById(id);
        }
    }

}
