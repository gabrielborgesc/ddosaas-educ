package com.ddosaas.central.model.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ddosaas.central.model.entity.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    List<Worker> findAllByMaxThreadsGreaterThanEqualOrderByIdDesc(Long numberOrThreads, Pageable pageable);
    
}
