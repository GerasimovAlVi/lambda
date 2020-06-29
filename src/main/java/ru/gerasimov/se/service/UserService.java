package ru.gerasimov.se.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gerasimov.se.api.repository.IUserRepository;
import ru.gerasimov.se.api.service.IUserService;
import ru.gerasimov.se.entity.Role;
import ru.gerasimov.se.entity.User;
import ru.gerasimov.se.enums.RoleType;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Optional;

@Data
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User saveUser(String login) {
        User user = findUser(login);
        Optional<User> userOptional = Optional.ofNullable(user);
        return userOptional.orElseGet(() -> {
            User newUser = new User();
            newUser.setLogin(login);
            iUserRepository.save(newUser);
            return newUser;
        });
    }

    private User findUser(String login){
        return iUserRepository.findByLogin(login);
    }

    @PostConstruct
    private void init(){
        initUser("admin", "admin", RoleType.ROLE_ADMIN);
        initUser("user", "user", RoleType.ROLE_USER);
    }

    @Transactional
    public void initUser(String login, String password, RoleType roleType){
        Optional<User> userOptional = Optional.ofNullable(iUserRepository.findByLogin(login));
        if(!userOptional.isPresent()){
            User user = new User();
            user.setLogin(login);
            user.setPass(passwordEncoder.encode(password));
            Role role = new Role();
            role.setName(roleType.name());
            user.setRoles(Collections.singleton(role));
            iUserRepository.save(user);
        }
    }
}
