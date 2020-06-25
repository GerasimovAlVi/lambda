package ru.gerasimov.se.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gerasimov.se.api.repository.IUserRepository;
import ru.gerasimov.se.api.service.IUserService;
import ru.gerasimov.se.entity.User;
import ru.gerasimov.se.enums.Role;

@Data
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    @Transactional
    public User saveUser(String login) {
        User user = findUser(login);
        if(user != null){
            return user;
        }
        user = new User();
        user.setLogin(login);
        iUserRepository.save(user);
        return user;
    }

    private User findUser(String login){
        return iUserRepository.findByLogin(login);
    }

    @Override
    @Transactional
    public void makeUserAnAdmin(User user){
        user.setRole(Role.ADMIN);
        iUserRepository.save(user);
    }
}
