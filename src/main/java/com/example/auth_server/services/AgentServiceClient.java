package com.example.auth_server.services;

import java.util.Map;

import com.example.auth_server.Models.RegisterRequest;

import reactor.core.publisher.Mono;

public interface AgentServiceClient {
    
    public Mono<Map> callSaveAgentAPI(Map<Object, Object> agent);

    public Map<Object, Object> saveAgent(RegisterRequest registerRequest);
    
}
