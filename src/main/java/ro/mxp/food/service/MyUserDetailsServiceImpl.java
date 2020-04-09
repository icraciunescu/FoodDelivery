package ro.mxp.food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.mxp.food.entity.MyUser;
import ro.mxp.food.repository.MyUserRepository;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private MyUserRepository myUserRepository;

    @Autowired
    public MyUserDetailsServiceImpl(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = myUserRepository.findByUsername(username);
        if (myUser != null) {
            return myUser;
        }
        throw new UsernameNotFoundException("masterAdmin " + username + " was not found");
    }

}
