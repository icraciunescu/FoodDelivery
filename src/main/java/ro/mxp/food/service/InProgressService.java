package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.InProgressDto;
import ro.mxp.food.repository.InProgressRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InProgressService {

    private ModelMapper modelMapper = new ModelMapper();

    private InProgressRepository inProgressRepository;
    @Autowired
    public InProgressService(InProgressRepository inProgressRepository) {
        this.inProgressRepository = inProgressRepository;
    }

    public List<InProgressDto> getAllInProgress() {
        return inProgressRepository.findAll()
                .stream()
                .map(inProgress -> modelMapper.map(inProgress, InProgressDto.class))
                .collect(Collectors.toList());
    }

    public void deleteInProgress(Long id) {
        inProgressRepository.deleteById(id);
    }

}
