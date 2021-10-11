package com.company.dto;

import com.company.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatDTO {

    private Integer id;
    private String name;
    private List<Integer> users;
    private LocalDateTime created_at;
    private LocalDateTime lastMessageDate;

}
