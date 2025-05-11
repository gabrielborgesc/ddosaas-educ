package com.ddosaas.central.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RunCentralDTO {
    
    @NotNull(message = "Informe a url.")
    private String url;
    
    @NotNull(message = "Informe o token.")
    private String token;

    private String internalToken = "$YHJNjfdg1532";
    
    @NotNull(message = "Informe a quantidade de threads.")
    @Min(value = 1, message = "O número de threads deve ser maior ou igual a 1.")
    private Long numberOfThreads;
    
}
