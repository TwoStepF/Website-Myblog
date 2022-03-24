package com.thisWebSite.myWebsite.Service;
import com.thisWebSite.myWebsite.model.User;
import com.thisWebSite.myWebsite.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private userRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("No user found " + username));
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getEncrytedPassword(),
                true, true, true, true,
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }

//    private Collection<? extends GrantedAuthority> getAuthorities(String role_user) {
//        return Collections.singletonList(new SimpleGrantedAuthority(role_user));
//    }

}
