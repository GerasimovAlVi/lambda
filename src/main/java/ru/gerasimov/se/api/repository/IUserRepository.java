package ru.gerasimov.se.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gerasimov.se.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}
