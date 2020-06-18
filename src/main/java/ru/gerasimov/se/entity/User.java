package ru.gerasimov.se.entity;

import lombok.Data;
import ru.gerasimov.se.enums.Role;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique=true, nullable=false)
    private Long id;

    @Column(length = 40, unique=true, nullable=false)
    private String login;

    @Column(length = 10, nullable=false)
    @Enumerated(value = EnumType.STRING)
    private Role role = Role.USER;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Message> messages;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "chats_list",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "char_room_id", nullable = false)
    )
    private Set<ChatRoom> chatRooms;
}
