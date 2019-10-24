package com.app.app.controller;

import com.app.app.model.dto.UfDTO;
import com.app.app.service.UfService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UfResource {

    private static final Logger logger = LoggerFactory.getLogger(UfResource.class);

    private final UfService service;

    @PostMapping("/uf/")
    @ApiOperation(value = "Cadastrar uf", response = UfDTO.class)
    public ResponseEntity<UfDTO> save(@Valid @RequestBody UfDTO uf){
        logger.info("Criando UF : {}", uf);
        UfDTO ufSave = service.save(uf);
        return ResponseEntity.ok(ufSave);
    }

    @GetMapping("/uf/{id}")
    @ApiOperation(value = "Buscar uf por id", response = UfDTO.class)
    public ResponseEntity<UfDTO> findById(@PathVariable Long id){
        logger.info("Find UF com id {}", id);
        UfDTO uf = service.findById(id);
        return ResponseEntity.ok(uf);
    }

    @DeleteMapping("/uf/{id}")
    @ApiOperation(value = "Remove uf por id", response = UfDTO.class)
    public ResponseEntity<UfDTO> deleteById(@PathVariable Long id){
        logger.info("Remove UF com id {}", id);
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/uf/findAll")
    @ApiOperation(value = "Buscar todas as uf", response = UfDTO.class)
    public ResponseEntity<List<UfDTO>> findAll(){
        logger.info("Find All UF");
        return ResponseEntity.ok(service.findAll());
    }
}
