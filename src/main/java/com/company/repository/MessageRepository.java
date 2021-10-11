package com.company.repository;

import com.company.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity,Integer> {

    @Query(value = "select * from message m where m.chat=:id order by m.created_at asc", nativeQuery = true)
    List<MessageEntity> chatAllMessages(@Param("id") Integer id);

}
