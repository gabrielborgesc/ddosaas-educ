package com.ddosaas.central.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
            throw new RuntimeException("Invalid Token.");
        }

        Pageable pageable = PageRequest.of(0, runDTO.getNumberOfIps(), Sort.by("id").descending());
        List<Worker> workerList = this.workerRepository.findAllByMaxThreadsGreaterThanEqualOrderByIdDesc(runDTO.getNumberOfThreads(), pageable);

        if(workerList.isEmpty() || workerList.size() < runDTO.getNumberOfIps()){
            throw new RuntimeException("No servers were found with the requested configurations.");
        }

        List<WorkerResponseDTO> workerResponseList = new CopyOnWriteArrayList<>();
        workerList.forEach(worker -> {
            try{
                runDTO.setInternalToken(worker.getInternalToken());
                this.workerService.run(worker, runDTO, workerResponseList);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            finally{
                workerResponseList.add(new WorkerResponseDTO());
            }
        });

        while(true) {
            
            Thread.sleep(100);
            if(workerResponseList.size() == workerList.size()){
                break;
            }

        }

    }

}
