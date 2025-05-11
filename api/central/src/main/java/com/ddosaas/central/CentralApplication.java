package com.ddosaas.central;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/*
 * A Annotation @EnableAutoConfiguration abaixo precisou ser inserida após a adição da biblioteca net.sf.ofx4j ao pom.xml.
 * Estava dando conflito com entre as versões do freemaker do spring e da lib.
 * Essa bilbioteca é para leitura de OFX
 */
@EnableAutoConfiguration(exclude = { FreeMarkerAutoConfiguration.class })
@EnableAsync
@SpringBootApplication
public class CentralApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(CentralApplication.class, args);
	}

}
