package com.example.crm_service;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClienteTest {

    @BeforeEach
    void configurarApi() {
        RestAssured.baseURI = "http://localhost:8087";
    }

    record clienteJson(String json, String nome, String documento, String email) {
    }

    private clienteJson cadastrarClienteJson() {
        String nome = "test-api" + UUID.randomUUID();
        String documento = UUID.randomUUID().toString().substring(0, 11);
        String email = nome + "@email.com";

        String json = """
                {
                  "nome": "%s",
                  "tipoDocumento": "CPF",
                  "documento": "%s",
                  "email": "%s",
                  "telefone": "11999999999",
                  "endereco": {
                    "cep": "01000-000",
                    "estado": "TA",
                    "cidade": "Teste API",
                    "bairro": "Teste API",
                    "rua": "Teste API",
                    "numero": "000"
                  }
                }
                """.formatted(nome, documento, email);
        return new clienteJson(json, nome, documento, email);
    }

    @Test
    void deveCadastrarCliente() {
        clienteJson cliente = cadastrarClienteJson();

        given()
                .contentType("application/json")
                .body(cliente.json)
                .log().all()
                .when()
                .post("/clientes")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", notNullValue())
                .body("nome", equalTo(cliente.nome))
                .body("tipoDocumento", not(empty()))
                .body("documento", equalTo(cliente.documento))
                .body("email", equalTo(cliente.email))
                .body("telefone", not(empty()))
                .body("endereco", notNullValue());

    }

    @Test
    void deveListarCliente() {
        clienteJson cliente = cadastrarClienteJson();

        //CADASTRAR CLIENTES
        given()
                .contentType("application/json")
                .body(cliente.json())
                .when()
                .post("/clientes")
                .then()
                .statusCode(201);


        //LISTAR CLIENTES
        given()
                .accept("application/json")
                .log().all()
                .when()
                .get("/clientes")
                .then()
                .log().all()
                .statusCode(200)
                .body("content", not(empty()));
    }

    @Test
    void deveAtualizarCliente() {
        clienteJson cliente = cadastrarClienteJson();

        //CADASTRAR CLIENTE
        String idCliente =
                given()
                        .contentType("application/json")
                        .body(cliente.json)
                        .when()
                        .post("/clientes")
                        .then()
                        .statusCode(201)
                        .extract()
                        .path("id");

        //ATUALIZAR CLIENTE
        String nome = "test-api" + UUID.randomUUID();
        String documento = UUID.randomUUID().toString().substring(0, 11);
        String email = nome + "@email.com";

        String json = """
                {
                  "nome": "%s",
                  "tipoDocumento": "CPF",
                  "documento": "%s",
                  "email": "%s",
                  "telefone": "11999999999",
                  "endereco": {
                    "cep": "01000-000",
                    "estado": "TA",
                    "cidade": "Teste API",
                    "bairro": "Teste API",
                    "rua": "Teste API",
                    "numero": "000"
                  },
                  "status": "ATIVO"
                }
                """.formatted(nome, documento, email);

        given()
                .contentType("application/json")
                .body(json)
                .when()
                .put("/clientes/" + idCliente)
                .then()
                .log().all()
                .statusCode(200)
                .body("id", notNullValue())
                .body("nome", equalTo(nome))
                .body("tipoDocumento", not(empty()))
                .body("documento", equalTo(documento))
                .body("email", equalTo(email))
                .body("telefone", not(empty()))
                .body("endereco", notNullValue());
    }

    @Test
    void deveDeletarCliente() {
        clienteJson cliente = cadastrarClienteJson();

        //CADASTRAR CLIENTE
        String idCliente =
                given()
                        .contentType("application/json")
                        .body(cliente.json)
                        .when()
                        .post("/clientes")
                        .then()
                        .statusCode(201)
                        .extract()
                        .path("id");

        //ATUALIZAR STATUS CLIENTE
        String nome = "test-api" + UUID.randomUUID();
        String documento = UUID.randomUUID().toString().substring(0, 11);
        String email = nome + "@email.com";

        String json = """
                {
                  "nome": "%s",
                  "tipoDocumento": "CPF",
                  "documento": "%s",
                  "email": "%s",
                  "telefone": "11999999999",
                  "endereco": {
                    "cep": "01000-000",
                    "estado": "TA",
                    "cidade": "Teste API",
                    "bairro": "Teste API",
                    "rua": "Teste API",
                    "numero": "000"
                  },
                  "status": "INATIVO"
                }
                """.formatted(nome, documento, email);

        given()
                .contentType("application/json")
                .body(json)
                .when()
                .put("/clientes/" + idCliente)
                .then()
                .statusCode(200);

        //DELETAR CLIENTE
        given()
                .accept("application/json")
                .log().all()
                .when()
                .delete("/clientes/" + idCliente)
                .then()
                .log().all()
                .statusCode(204);
    }

    @Test
    void naoDeveDeletarClienteComStatusAtivo() {
        clienteJson cliente = cadastrarClienteJson();

        //CADASTRAR CLIENTE
        String idCliente =
                given()
                        .contentType("application/json")
                        .body(cliente.json)
                        .when()
                        .post("/clientes")
                        .then()
                        .statusCode(201)
                        .body("status", equalTo("ATIVO"))
                        .extract()
                        .path("id");

        //DELETAR CLIENTE
        given()
                .accept("application/json")
                .log().all()
                .when()
                .delete("/clientes/" + idCliente)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Para deletar um cliente ele deve conter o status de INATIVO!"));
    }

}
