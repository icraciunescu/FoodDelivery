package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.InProgressDto;
import ro.mxp.food.entity.InProgress;
import ro.mxp.food.repository.InProgressRepository;
import ro.mxp.food.repository.RestaurantRepository;
import ro.mxp.food.utils.CurrentUsername;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InProgressService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CurrentUsername currentUsername;

    private ModelMapper modelMapper = new ModelMapper();

    private InProgressRepository inProgressRepository;

    @Autowired
    public InProgressService(InProgressRepository inProgressRepository) {
        this.inProgressRepository = inProgressRepository;
    }

    public List<InProgressDto> getAllInProgress() {
        List<InProgressDto> inProgressDtoList = inProgressRepository.findAll()
                .stream()
                .map(inProgress -> modelMapper.map(inProgress, InProgressDto.class))
                .collect(Collectors.toList());

        List<InProgressDto> inProgressDtoByRestaurant = new LinkedList<>();
        for (InProgressDto inProgressDto : inProgressDtoList) {
            if (inProgressDto.getCart().getProductInCartList().get(0).getProduct().getRestaurant().equals(restaurantRepository.findByUsername(currentUsername.displayCurrentUsername()))) {
                inProgressDtoByRestaurant.add(inProgressDto);
            }
        }

        return inProgressDtoByRestaurant;
    }

    public void deleteInProgress(Long id) {
        Optional<InProgress> inProgress = inProgressRepository.findById(id);
        InProgress thisInProgress = inProgress.get();
        if (thisInProgress.getCart().getProductInCartList().get(0).getProduct().getRestaurant().equals(restaurantRepository.findByUsername(currentUsername.displayCurrentUsername()))) {
            inProgressRepository.deleteById(thisInProgress.getId());
        }
    }

}
