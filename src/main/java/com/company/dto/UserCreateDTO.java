package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserCreateDTO {
    @NotBlank(message = "username kiritilmadi!!!")
    private String userName;
}
