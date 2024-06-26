package br.com.fiap.techchallenger4.spring.controller;

import br.com.fiap.estrutura.exception.BusinessException;
import br.com.fiap.estrutura.swagger.annotations.responses.ApiResponseBadRequestJson;
import br.com.fiap.estrutura.swagger.annotations.responses.ApiResponseNoContentJson;
import br.com.fiap.estrutura.utils.SpringControllerUtils;
import br.com.fiap.techchallenger4.logisticaentrega.dominio.controller.EntregadorController;
import br.com.fiap.techchallenger4.spring.jpa.entity.EntregadorEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Api Entregador")
public class EntregadorControllerSpring {

    @Autowired
    private EntregadorController entregadorController;


    @GetMapping(path = "/buscarEntregador", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar Entregador")
    public ResponseEntity<?> getEntregador(@RequestParam final Long idEntregador) throws BusinessException{
        
            return SpringControllerUtils.response(HttpStatus.OK, () -> entregadorController.getEntregadorPorId(idEntregador));

    }

    @PostMapping(path = "/criarEntregador", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponseNoContentJson
    @ApiResponseBadRequestJson
    @Operation(summary = "Cria um novo entregador")
    public ResponseEntity<?> criarEntregador(@RequestBody final EntregadorEntity entregador){

            return SpringControllerUtils.response(HttpStatus.OK, () -> entregadorController.criarEntregadorEntity(entregador));

    }
}
