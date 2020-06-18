package ru.gerasimov.se.api.repository;

import ru.gerasimov.se.entity.Message;

import javax.persistence.EntityManager;

public interface IMessageRepository {

    void saveMessage(EntityManager em, Message message);
}
