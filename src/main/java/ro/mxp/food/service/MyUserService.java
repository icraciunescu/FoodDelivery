package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.MyUserDto;
import ro.mxp.food.entity.MyUser;
import ro.mxp.food.repository.MyUserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserService {

    private ModelMapper modelMapper = new ModelMapper();

    private MyUserRepository myUserRepository;

    @Autowired
    public MyUserService(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    public List<MyUserDto> getAllMyUser() {
        return myUserRepository.findAll()
                .stream()
                .map(myUser -> modelMapper.map(myUser, MyUserDto.class))
                .collect(Collectors.toList());
    }

    public void addMyUser(MyUserDto myUserDto) throws Exception {
        List<MyUser> myUserList = myUserRepository.findAll();
        if (myUserList.isEmpty()) {
            myUserRepository.save(modelMapper.map(myUserDto, MyUser.class));
        } else {
            throw new Exception("admin exists!");
        }
    }

    public void updateMyUser(Long id, String email, String username, String password) {
        myUserRepository.updateMyUserRepo(id, email, username, password);
    }

    public void deleteMyUser(Long id) {
        myUserRepository.deleteById(id);
    }

}