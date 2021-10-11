package com.company.service;

import com.company.dto.ChatCreateDTO;
import com.company.dto.ChatDTO;
import com.company.dto.GetUserChatsDTO;
import com.company.entity.ChatEntity;
import com.company.entity.ChatUserEntity;
import com.company.entity.UserEntity;
import com.company.exp.ChatNameException;
import com.company.exp.UserNotFoundException;
import com.company.repository.ChatRepository;
import com.company.repository.ChatUserRepository;
import com.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatUserRepository chatUserRepository;

    @Autowired
    private UserRepository userRepository;

    public Integer addChat(ChatCreateDTO dto){

        Optional<ChatEntity> optional = chatRepository.findByName(dto.getName());

        if (optional.isPresent()) {
            throw new ChatNameException("nomi "+dto.getName()+" bo'lgan chat bazada oldindan mavjud, boshqa nom kiriting!!!");
        }

        ChatEntity entity = new ChatEntity();

        entity.setName(dto.getName());
        entity.setCreated_at(LocalDateTime.now());
        entity.setLastMessageDate(LocalDateTime.now());

        chatRepository.save(entity);

        for (Integer userId : dto.getUsers()) {
            addUsersToChat(userId,entity.getId());
        }

        return entity.getId();
    }

    public void addUsersToChat(Integer userId, Integer chatId){

        Optional<UserEntity> optional1 = userRepository.findById(userId);
        Optional<ChatEntity> optional2 = chatRepository.findById(chatId);

        if (optional1.isPresent() && optional2.isPresent()) {

            ChatUserEntity chatUserEntity = new ChatUserEntity();

            chatUserEntity.setUser(optional1.get());
            chatUserEntity.setChat(optional2.get());

            chatUserRepository.save(chatUserEntity);
        }
        return;
    }


    public List<ChatDTO> getUserAllChats(GetUserChatsDTO dto){

        Optional<UserEntity> optional = userRepository.findById(dto.getUserId());

        if (!optional.isPresent()) {
            throw new UserNotFoundException("user topilmadi!!!");
        }

        List<ChatEntity> list = chatRepository.getUserAllChats(dto.getUserId());

        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }


    public ChatDTO toDTO(ChatEntity entity){

        ChatDTO dto = new ChatDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreated_at(entity.getCreated_at());
        dto.setLastMessageDate(entity.getLastMessageDate());

        return dto;
    }
}
