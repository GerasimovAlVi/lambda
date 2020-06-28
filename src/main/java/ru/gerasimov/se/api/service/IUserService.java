package ru.gerasimov.se.api.service;

import ru.gerasimov.se.entity.User;

public interface IUserService {

    User saveUser(String login);
}
