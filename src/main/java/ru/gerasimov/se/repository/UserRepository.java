package ru.gerasimov.se.repository;

import org.springframework.stereotype.Repository;
import ru.gerasimov.se.api.repository.IUserRepository;
import ru.gerasimov.se.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class UserRepository implements IUserRepository {

    @Override
    public void saveUser(EntityManager em, User user) {
        em.persist(user);
    }

    @Override
    public User findUser(EntityManager em, String login) {
        Query query = em.createQuery("select u from User u where u.login = :login");
        query.setParameter("login", login);
        return (User) query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public void makeUserAnAdmin(EntityManager em, User user) {
        em.persist(user);
    }
}
