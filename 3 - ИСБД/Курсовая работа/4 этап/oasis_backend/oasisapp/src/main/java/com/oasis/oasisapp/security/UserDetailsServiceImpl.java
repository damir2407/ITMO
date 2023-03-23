package com.oasis.oasisapp.security;

import com.oasis.oasisapp.model.User;
import com.oasis.oasisapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.
                findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователя с логином " + username + " не существует"));
        return UserDetailsImpl.build(user);
    }
}
