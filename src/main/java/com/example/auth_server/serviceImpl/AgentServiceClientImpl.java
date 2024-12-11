package com.example.auth_server.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.auth_server.Models.RegisterRequest;
import com.example.auth_server.services.AgentServiceClient;

import reactor.core.publisher.Mono;


@Service
public class AgentServiceClientImpl implements AgentServiceClient {

    @Autowired
    private WebClient webClient;



    @Override
    public Mono<Map> callSaveAgentAPI(Map<Object, Object> agent) {

        return webClient.post()
        .uri("/saveAgent")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(agent))
        .retrieve()
        .bodyToMono(Map.class);
        
        
    }



    @Override
    public Map<Object, Object> saveAgent(RegisterRequest registerRequest) {

        Map<Object, Object> agent = new HashMap<>();

        Map<Object, Object> response = new HashMap<>();

        agent.put("agentCode", registerRequest.getAgent_code());
        agent.put("agentName", registerRequest.getUserName());
        agent.put("email", registerRequest.getEmail());

        callSaveAgentAPI(agent)
        .doOnSuccess(res -> response.put("response", res))
        .doOnError(res -> response.put("response", res))
        .subscribe();

        return response;


        
        
        
    }
    
}
