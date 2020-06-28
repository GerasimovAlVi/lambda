package ru.gerasimov.se.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gerasimov.se.api.repository.IUserRepository;
import ru.gerasimov.se.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = iUserRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("login not found");
        }

        return user;
    }
}
