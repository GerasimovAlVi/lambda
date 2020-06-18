package ru.gerasimov.se.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "app_chat_room")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique=true, nullable=false)
    private Long id;

    @Column(length = 40, name = "room_name", unique=true, nullable=false)
    private String roomName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "chats_list",
            joinColumns = @JoinColumn(name = "char_room_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false)
    )
    private Set<User> users;
}
