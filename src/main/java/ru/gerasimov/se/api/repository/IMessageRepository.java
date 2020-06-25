package ru.gerasimov.se.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gerasimov.se.entity.Message;

@Repository
public interface IMessageRepository extends JpaRepository<Message, Long> {}
