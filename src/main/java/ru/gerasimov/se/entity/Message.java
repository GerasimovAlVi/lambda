package ru.gerasimov.se.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "app_message")
public class Message {

    @Id
    private String id;

    @Column(name = "creation_date", nullable=false)
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    private String text;
}
