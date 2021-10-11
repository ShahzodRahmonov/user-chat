package com.company.repository;

import com.company.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<ChatEntity,Integer> {

    Optional<ChatEntity> findByName(String name);

    @Query(value = "select * from chat c where c.id in (select cu.chat_id from chat_user cu where cu.user_id=:id) order by c.last_message_date desc", nativeQuery = true)
    List<ChatEntity> getUserAllChats(@Param("id") Integer id);
}
