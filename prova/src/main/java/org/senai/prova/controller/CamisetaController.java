package org.senai.prova.controller;

import org.senai.prova.model.dto.CamisetaDTO;
import org.senai.prova.model.dto.CamisetaDisponivelDTO;
import org.senai.prova.service.CamisetaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/camiseta")
public class CamisetaController {

    private final CamisetaService camisetaService;

    public CamisetaController(CamisetaService camisetaService) {
        this.camisetaService = camisetaService;
    }

    @PostMapping("/add")
    public void camisetaAdd(@RequestBody CamisetaDTO camiseta){
        camisetaService.cadastrarCamiseta(camiseta);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CamisetaDTO>> listarCamisetas(){
        return new ResponseEntity<>(camisetaService.listarCamisetas(), HttpStatus.OK);
    }

    @PatchMapping("/alterarDisponibilidade/{codigo}")
    public ResponseEntity<String> alterarDisponibilidade(@RequestBody CamisetaDisponivelDTO dto,
                                                         @PathVariable("codigo") Long codigo){
        return new ResponseEntity<>(camisetaService.atualizarDisponibilidade(dto, codigo), HttpStatus.OK);
    }

}
