package com.ThoughtWorks.DDD.Order;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableDiscoveryClient
@EnableSwagger2
@SpringBootApplication
@EnableFeignClients
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder () {
		Jackson2ObjectMapperBuilder json = Jackson2ObjectMapperBuilder.json();
		json.featuresToEnable(SerializationFeature.WRAP_ROOT_VALUE);
		json.featuresToEnable(DeserializationFeature.UNWRAP_ROOT_VALUE);
		return json;
	}
}
