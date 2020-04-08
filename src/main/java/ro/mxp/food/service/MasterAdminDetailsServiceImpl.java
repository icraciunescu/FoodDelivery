package ro.mxp.food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.mxp.food.entity.MasterAdmin;
import ro.mxp.food.repository.MasterAdminRepository;

@Service
public class MasterAdminDetailsServiceImpl implements UserDetailsService {

    private MasterAdminRepository masterAdminRepository;

    @Autowired
    public MasterAdminDetailsServiceImpl(MasterAdminRepository masterAdminRepository) {
        this.masterAdminRepository = masterAdminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MasterAdmin masterAdmin = masterAdminRepository.findByUsername(username);
        if (masterAdmin != null) {
            return masterAdmin;
        }
        throw new UsernameNotFoundException("user " + username + " was not found!");
    }

}
