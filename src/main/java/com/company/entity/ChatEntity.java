package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "chat")
public class ChatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "users")
    private List<UserEntity> users;

    @Column(name = "created_at")
    private LocalDateTime created_at;
    @Column(name = "lastMessageDate")
    private LocalDateTime lastMessageDate;
}
