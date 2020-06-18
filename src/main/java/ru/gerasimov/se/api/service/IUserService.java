package ru.gerasimov.se.api.service;

import ru.gerasimov.se.entity.User;

import javax.persistence.EntityManager;

public interface IUserService {

    User saveUser(EntityManager em, String login);

    void makeUserAnAdmin(EntityManager em, User user);
}
