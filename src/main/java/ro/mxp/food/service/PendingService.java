package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.PendingDto;
import ro.mxp.food.repository.PendingRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PendingService {

    private ModelMapper modelMapper = new ModelMapper();

    private PendingRepository pendingRepository;
    @Autowired
    public PendingService(PendingRepository pendingRepository) {
        this.pendingRepository = pendingRepository;
    }

    public List<PendingDto> getAllPending() {
        return pendingRepository.findAll()
                .stream()
                .map(productForDelivery -> modelMapper.map(productForDelivery, PendingDto.class))
                .collect(Collectors.toList());
    }

}
