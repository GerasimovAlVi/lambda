package ru.gerasimov.se.repository;

import org.springframework.stereotype.Repository;
import ru.gerasimov.se.api.repository.IMessageRepository;
import ru.gerasimov.se.entity.Message;

import javax.persistence.EntityManager;

@Repository
public class MessageRepository implements IMessageRepository {

    @Override
    public void saveMessage(EntityManager em, Message message) {
        em.persist(message);
    }
}
