package ro.mxp.food.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.mxp.food.dto.MyUserDto;
import ro.mxp.food.entity.MyUser;
import ro.mxp.food.repository.MyUserRepository;
import ro.mxp.food.utils.CurrentUsername;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserService {

    @Autowired
    private BCryptPasswordEncoder getBCryptPasswordEncoder;
    @Autowired
    private CurrentUsername currentUsername;

    private final ModelMapper modelMapper = new ModelMapper();

    private final MyUserRepository myUserRepository;
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
            myUserDto.setRole("ADMIN");
            myUserDto.setPassword(getBCryptPasswordEncoder.encode(myUserDto.getPassword()));
            myUserRepository.save(modelMapper.map(myUserDto, MyUser.class));
        } else {
            throw new Exception("admin exists!");
        }
    }

    public void updateMyUser(Long id, String email, String username) {
        MyUser myUser = myUserRepository.findByUsername(currentUsername.displayCurrentUsername());
        if (myUser != null && myUser.getId().equals(id)) {
            myUserRepository.updateMyUserRepo(id, email, username);
        }
    }

    public void deleteMyUser(Long id) throws NullPointerException{
        MyUser myUser = myUserRepository.findByUsername(currentUsername.displayCurrentUsername());
        List<MyUser> myUserList = myUserRepository.findAll();
        if (myUserList.size() > 1 && myUser != null && myUser.getId().equals(id)) {
            throw new NullPointerException("!!! you are ADMIN !!!");
        } else if (myUserList.size() > 1 && myUser != null && !myUser.getId().equals(id)) {
            myUserRepository.deleteById(id);
        }
    }

}