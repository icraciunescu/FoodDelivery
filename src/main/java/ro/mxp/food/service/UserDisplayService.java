package ro.mxp.food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mxp.food.entity.MyUser;
import ro.mxp.food.repository.MyUserRepository;
import ro.mxp.food.utils.CurrentUsername;

@Service
public class UserDisplayService {

    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    private CurrentUsername currentUsername;

    public MyUser currentUserName() {

        return myUserRepository.findByUsername(currentUsername.displayCurrentUsername());

    }

}
