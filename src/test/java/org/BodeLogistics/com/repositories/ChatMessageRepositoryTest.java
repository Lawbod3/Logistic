package org.BodeLogistics.com.repositories;

import org.BodeLogistics.com.data.models.ChatMessage;
import org.BodeLogistics.com.data.repositories.ChatMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class ChatMessageRepositoryTest {
    @Autowired
    ChatMessageRepository chatMessageRepository;




    @BeforeEach
    public void setUp() {
        chatMessageRepository.deleteAll();
    }
    @Test
    public void testRepositoryIsEmpty() {
        assertTrue(chatMessageRepository.findAll().isEmpty());
    }
    @Test
    public void testRepositoryIsNotEmpty() {
        chatMessageRepository.save(new ChatMessage());
        assertFalse(chatMessageRepository.findAll().isEmpty());
    }
    @Test
    public void testFindByActivityId() {
        chatMessageRepository.save(new ChatMessage());
        List<ChatMessage> result = chatMessageRepository.findByActivity("122344556778976434");
        assertTrue(result.isEmpty());

    }
}
