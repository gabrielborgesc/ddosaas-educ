package com.ddosaas.attacker.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class RunAttackerDTO {
    
    private String url;
    private String internalToken;
    private Long numberOfThreads;

}
