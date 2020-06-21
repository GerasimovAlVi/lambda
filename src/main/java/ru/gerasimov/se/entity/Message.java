package ru.gerasimov.se.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "app_message")
public class Message extends AbstractEntity {

    @Column(name = "creation_date", nullable=false)
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    private String text;
}
