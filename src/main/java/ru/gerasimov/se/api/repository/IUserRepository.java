package ru.gerasimov.se.api.repository;

import ru.gerasimov.se.entity.User;

import javax.persistence.EntityManager;

public interface IUserRepository {

    void saveUser(EntityManager em, User user);

    User findUser(EntityManager em, String login);

    void makeUserAnAdmin(EntityManager em, User user);
}
