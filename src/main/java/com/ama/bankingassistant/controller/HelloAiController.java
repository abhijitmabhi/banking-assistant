package com.ama.bankingassistant.controller;

import com.ama.bankingassistant.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloAiController {
    private final ChatService chatService;

    public HelloAiController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String question) {
        return chatService.ask(question);
    }

    @GetMapping("/ask/verbose")
    public String askPost(@RequestParam String question) {
        return chatService.askWithLogging(question);
    }
}
