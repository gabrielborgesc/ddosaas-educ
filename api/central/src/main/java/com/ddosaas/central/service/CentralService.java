package com.ddosaas.central.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddosaas.central.api.dto.RunCentralDTO;
import com.ddosaas.central.api.dto.WorkerResponseDTO;
import com.ddosaas.central.model.entity.Worker;
import com.ddosaas.central.model.repository.TokenRepository;
import com.ddosaas.central.model.repository.WorkerRepository;

import jakarta.transaction.Transactional;
import lombok.SneakyThrows;

@Service
@Transactional
public class CentralService {
    
    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private WorkerService workerService;

    @SneakyThrows
    public void run(RunCentralDTO runDTO) {

        boolean existsByToken = this.tokenRepository.existsByToken(runDTO.getToken());
        if(!existsByToken){
            throw new RuntimeException("Token inválido.");
        }

        List<Worker> workerList = this.workerRepository.findAllByMaxThreadsGreaterThanEqualOrderByIdDesc(runDTO.getNumberOfThreads());

        if(workerList.isEmpty()){
            throw new RuntimeException("Não foram encontrados servidores com os parâmetros requisitados.");
        }

        List<WorkerResponseDTO> workerResponseList = new CopyOnWriteArrayList<>();
        workerList.forEach(worker -> this.workerService.run(worker, runDTO, workerResponseList));

        // long initialTime = System.currentTimeMillis();
        while(true) {
            
            // long currentTime = System.currentTimeMillis();
            // if( (currentTime - initialTime) > ConstantesUtil.timeoutWorkerCallInMiliseconds){
            //     //timeout de 60s.
            //     throw new RuntimeException("Ocorreu um erro ao gerar $nomeRecurso. <br/> Por favor, tente novamente.".replace("$nomeRecurso", nomeRecurso));
            // }

            Thread.sleep(100);
            if(workerResponseList.size() == workerList.size()){
                break;
            }

        }        

    }

}
