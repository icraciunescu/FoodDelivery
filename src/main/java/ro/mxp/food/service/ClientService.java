package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.ClientDto;
import ro.mxp.food.entity.Client;
import ro.mxp.food.repository.ClientRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

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
        clientRepository.save(modelMapper.map(clientDto, Client.class));
    }

    public void updateClient(Long id, String email, String username, String password, String phoneNumber, String firstName, String lastName, Date dateOfBirth) {
        clientRepository.updateClientRepo(id, email, username, password, phoneNumber, firstName, lastName, dateOfBirth);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

}