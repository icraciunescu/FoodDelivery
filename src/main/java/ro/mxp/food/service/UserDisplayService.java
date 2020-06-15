package ro.mxp.food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mxp.food.entity.MyUser;
import ro.mxp.food.repository.MyUserRepository;
import ro.mxp.food.utils.CurrentUsername;

@Service
public class UserDisplayService {

    private final MyUserRepository myUserRepository;
    private final CurrentUsername currentUsername;

    @Autowired
    public UserDisplayService(MyUserRepository myUserRepository, CurrentUsername currentUsername) {
        this.myUserRepository = myUserRepository;
        this.currentUsername = currentUsername;
    }

    public MyUser currentUserName() {

        return myUserRepository.findByUsername(currentUsername.displayCurrentUsername());

    }

}
