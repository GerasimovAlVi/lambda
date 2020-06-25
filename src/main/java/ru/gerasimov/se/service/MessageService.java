package ru.gerasimov.se.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gerasimov.se.api.repository.IMessageRepository;
import ru.gerasimov.se.api.service.IMessageService;
import ru.gerasimov.se.entity.Message;
import ru.gerasimov.se.entity.User;

import java.time.LocalDateTime;

@Service
public class MessageService implements IMessageService {

    @Autowired
    private IMessageRepository iMessageRepository;

    @Override
    @Transactional
    public void saveMessage(String text, User user) {
        Message message = new Message();
        message.setCreationDate(LocalDateTime.now());
        message.setUser(user);
        message.setText(text);
        iMessageRepository.save(message);
    }
}
