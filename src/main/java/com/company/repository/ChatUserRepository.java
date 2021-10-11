package com.company.repository;

import com.company.entity.ChatEntity;
import com.company.entity.ChatUserEntity;
import com.company.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatUserRepository extends JpaRepository<ChatUserEntity,Integer> {

    Optional<ChatUserEntity> findByUserAndChat(UserEntity userEntity, ChatEntity chatEntity);

}
