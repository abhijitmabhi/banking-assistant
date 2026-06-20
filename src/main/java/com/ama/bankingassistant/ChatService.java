package com.ama.bankingassistant;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ChatService {
    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("""
                        You are a helpful assistant for a banking application.
                        Answer clearly and concisely.
                        Always respond in the language the user writes in.
                        """)
                .build();
    }

    // Basic call - return plain text
    public String ask(String question) {
        return chatClient
                .prompt()
                .user(question)
                .call()
                .content();
    }

    // Returns full response with token use logged
    public String askWithLogging(String question) {
        ChatResponse response = chatClient
                .prompt()
                .user(question)
                .call()
                .chatResponse();

        Objects.requireNonNull(response, "No response from AI model");

        var usage = response.getMetadata().getUsage();
        int inputTokens = usage.getPromptTokens();
        int totalTokens = usage.getTotalTokens();
        int outputTokens = totalTokens - inputTokens;

        System.out.printf("[Tokens] input: %d | output: %d | total: %d%n",
                inputTokens, outputTokens, totalTokens);

        return Objects.requireNonNull(response.getResult(), "No result in response")
                .getOutput()
                .getText();
    }
}
