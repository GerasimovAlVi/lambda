package ru.gerasimov.se.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "app_chat_room")
public class ChatRoom {

    @Id
    private String id;

    @Column(length = 40, name = "room_name", nullable=false)
    private String roomName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "chats_list",
            joinColumns = @JoinColumn(name = "char_room_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false)
    )
    private Set<User> users;
}
