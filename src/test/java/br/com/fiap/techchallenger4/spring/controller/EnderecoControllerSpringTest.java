package br.com.fiap.techchallenger4.spring.controller;

import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;


@Profile("dev")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EnderecoControllerSpringTest {

	@LocalServerPort
	private int porta;

	@BeforeEach
	void setUp() {
		RestAssured.port = porta;
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}
	
	@Test
	void deveBuscarEnderecoPorCep() {
		given()	
			.queryParam("cep", "11060002")
		.when()
			.get("/buscarenderecos")
		.then()
			.statusCode(org.apache.http.HttpStatus.SC_OK)
		;
	}
	
	@Test
	void deveBuscarEnderecoPorCepJaRegistradoNoBanco() {
		given()	
			.queryParam("cep", "11533180")
		.when()
			.get("/buscarenderecos")
		.then()
			.statusCode(org.apache.http.HttpStatus.SC_OK)
		;
	}
	
	@Test
	void naoDeveBuscarEnderecoPorCepCasoStringComApenasEspaco() {
		given()	
			.queryParam("cep", "    ")
		.when()
			.get("/buscarenderecos")
		.then()
			.statusCode(org.apache.http.HttpStatus.SC_BAD_REQUEST)
			.body("message", is("CEP é obrigatório para realizar a busca"))
		;
	}
	
	@Test
	void naoDeveBuscarEnderecoPorCepCasoNaoEncontradoNoEndpoint() {
		given()	
			.queryParam("cep", "11000000")
		.when()
			.get("/buscarenderecos")
		.then()
			.statusCode(org.apache.http.HttpStatus.SC_BAD_REQUEST)
			.body("message", is("404 Not Found: \"{\"code\":\"not_found\",\"message\":\"O CEP 11000000 nao foi encontrado\"}\""))
		;
	}

}