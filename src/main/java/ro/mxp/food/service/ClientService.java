package ro.mxp.food.service;

import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.ClientDto;
import ro.mxp.food.entity.Client;
import ro.mxp.food.repository.ClientRepository;
import ro.mxp.food.repository.MyUserRepository;
import ro.mxp.food.utils.CurrentUsername;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final BCryptPasswordEncoder getBCryptPasswordEncoder;
    private final CurrentUsername currentUsername;
    private final MyUserRepository myUserRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(BCryptPasswordEncoder getBCryptPasswordEncoder, CurrentUsername currentUsername, MyUserRepository myUserRepository,
                         ClientRepository clientRepository) {
        this.getBCryptPasswordEncoder = getBCryptPasswordEncoder;
        this.currentUsername = currentUsername;
        this.myUserRepository = myUserRepository;
        this.clientRepository = clientRepository;
    }

    public List<ClientDto> getAllClient() {
        List<ClientDto> clientDtoList = new LinkedList<>();
        if (clientRepository.findByUsername(currentUsername.displayCurrentUsername()) == null) {
            List<ClientDto> allClientDtoList = clientRepository.findAll()
                    .stream()
                    .map(client -> modelMapper.map(client, ClientDto.class))
                    .collect(Collectors.toList());
            clientDtoList.addAll(allClientDtoList);
        } else {
            Client client = clientRepository.findByUsername(currentUsername.displayCurrentUsername());
            clientDtoList.add(modelMapper.map(client, ClientDto.class));
        }
        return clientDtoList;
    }

    public void addClient(@NotNull ClientDto clientDto) {
        clientDto.setRole("CLIENT");
        clientDto.setPassword(getBCryptPasswordEncoder.encode(clientDto.getPassword()));
        clientRepository.save(modelMapper.map(clientDto, Client.class));
    }

    public void updateClient(Long id, String email, String username, String phoneNumber, String firstName, String lastName, Date dateOfBirth) {
        Client client = clientRepository.findByUsername(currentUsername.displayCurrentUsername());
        if (client != null && client.getId().equals(id)) {
            clientRepository.updateClient(id, email, username, phoneNumber, firstName, lastName, dateOfBirth);
        }
    }

    public void deleteClient(Long id) {
        Client client = clientRepository.findByUsername(currentUsername.displayCurrentUsername());
        if ((client != null && client.getId().equals(id)) || myUserRepository.findByUsername(currentUsername.displayCurrentUsername()) != null) {
            clientRepository.deleteById(id);
        }
    }

}
