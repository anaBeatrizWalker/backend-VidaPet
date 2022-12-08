package br.fatec.vidapet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI vidapetOpenAPI() {
		return new OpenAPI().info(new Info().title("API do Projeto do Sistema VidaPet")
				.description("Está é a documentação da API do sistema de gerenciamento para clínicas veterinárias, VidaPet").version("v0.0.1")
				.contact(new Contact().name("Ana Beatriz da Conceição Walker").email("anabeatriz@anabeatriz.com"))
				.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}
