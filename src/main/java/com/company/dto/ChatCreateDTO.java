package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class ChatCreateDTO {

    @NotBlank(message = "name kiritilmadi!!!")
    private String name;
    @NotEmpty(message = "Bitta ham user id kiritmadingiz, bir nechtagacha kiritish mumkin!!!")
    private List<Integer> users;

}
