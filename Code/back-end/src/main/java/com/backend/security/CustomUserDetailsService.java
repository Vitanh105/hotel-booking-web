package com.backend.security;

import com.backend.model.User;
import com.backend.responsitory.UserResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserResponsitory userResponsitory;
    @Autowired
    public CustomUserDetailsService(UserResponsitory userResponsitory){
        this.userResponsitory=userResponsitory;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userResponsitory.findByUsername(username);

        // kiem tra null
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    getAuthorities(user));
        } else {
            throw new UsernameNotFoundException("Invalid username or password!");
        }
    }

        private Collection<? extends GrantedAuthority> getAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRoleType().name())); // thêm ROLE_ trước roletype
        return authorities;
    }
    
}
