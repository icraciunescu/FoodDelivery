package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.InProgressDto;
import ro.mxp.food.dto.PendingCartDto;
import ro.mxp.food.entity.InProgress;
import ro.mxp.food.entity.PendingCart;
import ro.mxp.food.repository.InProgressRepository;
import ro.mxp.food.repository.PendingCartRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PendingCartService {

    @Autowired
    private InProgressRepository inProgressRepository;

    private ModelMapper modelMapper = new ModelMapper();

    private PendingCartRepository pendingCartRepository;
    @Autowired
    public PendingCartService(PendingCartRepository pendingCartRepository) {
        this.pendingCartRepository = pendingCartRepository;
    }

    public List<PendingCartDto> getAllPendingCart() {
        return pendingCartRepository.findAll()
                .stream()
                .map(productForDelivery -> modelMapper.map(productForDelivery, PendingCartDto.class))
                .collect(Collectors.toList());
    }

    public void inProgress(Long id) {
        Optional<PendingCart> optionalInProgress = pendingCartRepository.findById(id);
        PendingCart pendingCart = optionalInProgress.get();
        InProgressDto inProgressDto = modelMapper.map(pendingCart, InProgressDto.class);
        InProgress inProgress = modelMapper.map(inProgressDto, InProgress.class);
        inProgressRepository.save(inProgress);
        pendingCartRepository.deleteById(id);
    }

    public void deletePendingCart(Long id) {
        pendingCartRepository.deleteById(id);
    }

}
