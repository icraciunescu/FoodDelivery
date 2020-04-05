package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.MasterAdminDto;
import ro.mxp.food.entity.MasterAdmin;
import ro.mxp.food.repository.MasterAdminRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MasterAdminService {

    private ModelMapper modelMapper = new ModelMapper();

    private MasterAdminRepository masterAdminRepository;

    public MasterAdminService(MasterAdminRepository masterAdminRepository) {
        this.masterAdminRepository = masterAdminRepository;
    }

    public List<MasterAdminDto> getAllMasterAdmin() {
        return masterAdminRepository.findAll()
                .stream()
                .map(masterAdmin -> modelMapper.map(masterAdmin, MasterAdminDto.class))
                .collect(Collectors.toList());
    }

    public void addMasterAdmin(MasterAdminDto masterAdminDto) throws Exception {
        List<MasterAdmin> masterAdminList = masterAdminRepository.findAll();
        if (masterAdminList.isEmpty()) {
            masterAdminRepository.save(modelMapper.map(masterAdminDto, MasterAdmin.class));
        } else {
            throw new Exception("admin exists!");
        }

    }

    public void updateMasterAdminService(Long id, String email, String username, String password) {
        masterAdminRepository.updateMasterAdmin(id, email, username, password);
    }

}
