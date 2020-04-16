package ro.mxp.food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.mxp.food.entity.MyUser;
import ro.mxp.food.repository.MyUserRepository;

@Service
public class UserDisplayService {

    @Autowired
    private MyUserRepository myUserRepository;

    public MyUser currentUserName() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        MyUser myUser = myUserRepository.findByUsername(currentUsername);
        return myUser;

    }

}
