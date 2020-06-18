package ru.gerasimov.se.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gerasimov.se.api.repository.IUserRepository;
import ru.gerasimov.se.api.service.IUserService;
import ru.gerasimov.se.entity.User;
import ru.gerasimov.se.enums.Role;

import javax.persistence.EntityManager;

@Data
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public User saveUser(EntityManager em, String login) {
        em.getTransaction().begin();
        User user = findUser(em, login);
        if(user != null){
            return user;
        }
        user = new User();
        user.setLogin(login);
        iUserRepository.saveUser(em, user);
        em.getTransaction().commit();
        return user;
    }

    private User findUser(EntityManager em, String login){
        return iUserRepository.findUser(em, login);
    }

    @Override
    public void makeUserAnAdmin(EntityManager em, User user){
        em.getTransaction().begin();
        user.setRole(Role.ADMIN);
        iUserRepository.makeUserAnAdmin(em, user);
        em.getTransaction().commit();
    }
}
