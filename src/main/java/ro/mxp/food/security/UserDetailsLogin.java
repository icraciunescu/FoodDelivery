package ro.mxp.food.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ro.mxp.food.entity.MyUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UserDetailsLogin implements UserDetails {

    private final MyUser myUser;

    public UserDetailsLogin(MyUser myUser) {
        this.myUser = myUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        getRoleList().forEach(r -> {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
            authorities.add(authority);
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return myUser.getPassword();
    }

    @Override
    public String getUsername() {
        return myUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<String> getRoleList(){
        if(myUser.getRole().length() > 0){
            return Arrays.asList(myUser.getRole().split(","));
        }
        return new ArrayList<>();
    }

}
