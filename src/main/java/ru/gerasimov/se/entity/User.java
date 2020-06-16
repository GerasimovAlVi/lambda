package ru.gerasimov.se.entity;

import lombok.Getter;
import lombok.Setter;
import ru.gerasimov.se.enums.Role;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "app_user")
public class User {

    @Id
    private String id;

    @Column(length = 40, nullable=false)
    private String login;

    @Column(length = 10, nullable=false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Message> messages;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "chats_list",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "char_room_id", nullable = false)
    )
    private Set<ChatRoom> chatRooms;
}
