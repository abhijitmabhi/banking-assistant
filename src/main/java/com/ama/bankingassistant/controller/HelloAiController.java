package com.ama.bankingassistant.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloAiController {
    private final ChatClient chatClient;

    public HelloAiController(ChatClient.Builder builder) {
        this.chatClient = builder.defaultSystem("""
                        You are a helpful assistant for a banking application.
                        Answer clearly and concisely.
                        """)
                .build();
    }

    @GetMapping("/ask")
    public String ask(
            @RequestParam String question
    ) {
        return chatClient
                .prompt()
                .user(question)
                .call()
                .content();
    }
}
