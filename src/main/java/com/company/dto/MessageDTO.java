package com.company.dto;

import com.company.entity.ChatEntity;
import com.company.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
public class MessageDTO {

    private Integer id;
    private Integer chatId;
    private Integer authorId;
    private String text;
    private LocalDateTime created_at;

}
