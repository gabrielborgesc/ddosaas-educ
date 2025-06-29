package com.ddosaas.central.service;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.ddosaas.central.api.dto.RunCentralDTO;
import com.ddosaas.central.api.dto.WorkerResponseDTO;
import com.ddosaas.central.model.entity.Worker;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class WorkerService {

    @Async
    public void run(Worker worker, RunCentralDTO runCentralDTO, List<WorkerResponseDTO> workerResponseList) {

        String internalToken = worker.getInternalToken();
        RunCentralDTO runWorker = runCentralDTO.clone();
        runWorker.setInternalToken(internalToken);

        log.info("Iniciando ataque em {} utilizando o worker {} ", runWorker.getUrl(), worker.getUrl());

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<>(runWorker, headers);
        String url = worker.getUrl() + "/" + "/api/ddosaas/run";
        new RestTemplate().postForObject(url, requestEntity, String.class);
        
        log.info("Finalizado ataque em {} utilizando o worker {}", runWorker, worker.getUrl());
    }
    
}
