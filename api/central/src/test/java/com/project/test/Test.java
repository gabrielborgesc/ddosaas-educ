package com.project.test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.Getter;

@Getter
public class Test {
	
//	private MySimpleDateFormat mySimpleDateFormat = new MySimpleDateFormat("dd-MM-yyyy");
	
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy - HH:mm:ss")
//	private LocalDateTime date;
	
    private static final String TARGET_URL = "http://localhost";
    private static final int THREAD_COUNT = 30;

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        List<Future<?>> futures = new ArrayList<>();

        System.out.println("Iniciando ataque DDoS simulado com " + THREAD_COUNT + " threads...");

        Runnable task = () -> {
            while (true) {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(TARGET_URL).openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(1000);
                    connection.setReadTimeout(1000);
                    int responseCode = connection.getResponseCode();
                    System.out.println(Thread.currentThread().getName() + " - Resposta: " + responseCode);
                    // connection.disconnect();

                    // Interrompe a thread se não for 200
                    if (responseCode != 200) {
                        break;
                    }

                } catch (Exception e) {
                    System.err.println(Thread.currentThread().getName() + " - Erro: " + e.getMessage());
                    break; // Encerra thread em caso de erro
                }
            }
        };

        // Envia todas as tarefas
        for (int i = 0; i < THREAD_COUNT; i++) {
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

        System.out.println("Ataque finalizado. Todas as threads encerraram.");
    }
	
}