package com.company.service;

import com.company.dto.GetChatMessagesDTO;
import com.company.dto.MessageCreateDTO;
import com.company.dto.MessageDTO;
import com.company.entity.ChatEntity;
import com.company.entity.ChatUserEntity;
import com.company.entity.MessageEntity;
import com.company.entity.UserEntity;
import com.company.exp.ChatNotFoudException;
import com.company.exp.UserNotFoundException;
import com.company.repository.ChatRepository;
import com.company.repository.ChatUserRepository;
import com.company.repository.MessageRepository;
import com.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    ChatUserRepository chatUserRepository;

    public Integer addMessage(MessageCreateDTO dto){

        Optional<UserEntity> optional1 = userRepository.findById(dto.getAuthorId());

        if (!optional1.isPresent()) {
            throw new UserNotFoundException("user topilmadi!!!");
        }

        Optional<ChatEntity> optional2 = chatRepository.findById(dto.getChatId());

        if (!optional2.isPresent()) {
            throw new UserNotFoundException("chat topilmadi!!!");
        }

        Optional<ChatUserEntity> optional = chatUserRepository.findByUserAndChat(optional1.get(),optional2.get());

        if (!optional.isPresent()) {
            throw new UserNotFoundException("siz bu chat foydalanuvchilari ro'yhatida mavjud emassiz!!!");
        }

        ChatEntity chatEntity = optional2.get();

        MessageEntity entity = new MessageEntity();

        entity.setAuthor(optional1.get());
        entity.setChat(chatEntity);
        entity.setText(dto.getText());
        entity.setCreated_at(LocalDateTime.now());

        messageRepository.save(entity);

        chatEntity.setLastMessageDate(LocalDateTime.now());
        chatRepository.save(chatEntity);

        return entity.getId();
    }


    public List<MessageDTO> getChatAllMessages(GetChatMessagesDTO dto){

        Optional<ChatEntity> optional = chatRepository.findById(dto.getChatId());
        if (!optional.isPresent()) {
            throw new ChatNotFoudException("Chat topilmadi");
        }

        List<MessageEntity> list = messageRepository.chatAllMessages(dto.getChatId());

        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }


    public MessageDTO toDTO(MessageEntity entity){

        MessageDTO dto = new MessageDTO();
        dto.setId(entity.getId());
        dto.setAuthorId(entity.getAuthor().getId());
        dto.setChatId(entity.getChat().getId());
        dto.setText(entity.getText());
        dto.setCreated_at(entity.getCreated_at());

        return dto;
    }
}
