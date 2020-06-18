package ru.gerasimov.se.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gerasimov.se.api.repository.IMessageRepository;
import ru.gerasimov.se.api.service.IMessageService;
import ru.gerasimov.se.entity.Message;
import ru.gerasimov.se.entity.User;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Service
public class MessageService implements IMessageService {

    @Autowired
    private IMessageRepository iMessageRepository;

    @Override
    public void saveMessage(EntityManager em, String text, User user) {
        em.getTransaction().begin();
        Message message = new Message();
        message.setCreationDate(LocalDateTime.now());
        message.setUser(user);
        message.setText(text);
        iMessageRepository.saveMessage(em, message);
        em.getTransaction().commit();
    }
}
