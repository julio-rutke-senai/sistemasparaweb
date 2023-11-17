package br.senai.sc.edu.aulaapi.controller;

import br.senai.sc.edu.aulaapi.model.Usuario;
import br.senai.sc.edu.aulaapi.model.dto.NovoUsuario;
import br.senai.sc.edu.aulaapi.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> salvar(@RequestBody NovoUsuario usuario){
        Usuario usuarioCriado = usuarioService.salvar(usuario);
        return new ResponseEntity(usuarioCriado, HttpStatus.CREATED);
    }

}
