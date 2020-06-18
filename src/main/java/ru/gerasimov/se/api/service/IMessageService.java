package ru.gerasimov.se.api.service;

import ru.gerasimov.se.entity.User;

import javax.persistence.EntityManager;

public interface IMessageService {

    void saveMessage(EntityManager em, String message, User user);
}
