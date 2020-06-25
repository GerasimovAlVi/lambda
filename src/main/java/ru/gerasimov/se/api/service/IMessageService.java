package ru.gerasimov.se.api.service;

import ru.gerasimov.se.entity.User;

public interface IMessageService {

    void saveMessage(String message, User user);
}
