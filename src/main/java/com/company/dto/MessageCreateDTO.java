package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MessageCreateDTO {

    @NotNull(message = "chat id kiritilmadi!!!")
    private Integer chatId;
    @NotNull(message = "author id kiritilmadi!!!")
    private Integer authorId;
    @NotBlank(message = "xabar matni kiritilmadi!!!")
    private String text;

}
