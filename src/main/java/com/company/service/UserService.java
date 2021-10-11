package com.company.service;

import com.company.dto.UserCreateDTO;
import com.company.entity.UserEntity;
import com.company.exp.UserItemException;
import com.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Integer addUser(UserCreateDTO dto){

        Optional<UserEntity> optional = userRepository.findByUserName(dto.getUserName());
        if (optional.isPresent()) {
            throw new UserItemException("Bu usermane bazada oldindan mavjud, boshqa username kiriting!!!");
        }

        UserEntity entity = new UserEntity();

        entity.setUserName(dto.getUserName());
        entity.setCreated_at(LocalDateTime.now());

        userRepository.save(entity);

        return entity.getId();
    }
}
