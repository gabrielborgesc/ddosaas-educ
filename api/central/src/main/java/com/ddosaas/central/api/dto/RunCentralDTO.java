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
public class RunCentralDTO implements Cloneable {
    
    @NotNull(message = "Please provide the URL.")
    private String url;

    @NotNull(message = "Please provide the token.")
    private String token;

    private String internalToken;

    @NotNull(message = "Please provide the number of threads.")
    @Min(value = 1, message = "The number of threads must be greater than or equal to 1.")
    private Long numberOfThreads;

    @NotNull(message = "Please provide the number of IPs.")
    @Min(value = 1, message = "The number of IPs must be greater than or equal to 1.")
    private int numberOfIps;

    @Override
    public RunCentralDTO clone() {
        try {
            return (RunCentralDTO) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }    
    
    
}
