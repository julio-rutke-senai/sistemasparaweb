package br.senai.sc.edu.aulaapi.controller;

import br.senai.sc.edu.aulaapi.model.Aluno;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunosController {

    private List<Aluno> listaAlunos = new ArrayList<>();
    
    
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String criarAluno(@RequestBody Aluno aluno){
        listaAlunos.add(aluno);
        return "Aluno adicionado com sucesso";
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Aluno>> listarAlunos(){
        return new ResponseEntity<>(listaAlunos, HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Aluno> getAluno(@PathVariable("codigo") Long codigo){
        Aluno first = listaAlunos.stream().filter(a -> a.getCodigo() == codigo).findFirst().get();
        return new ResponseEntity<>(first, HttpStatus.OK);
    }
    @PutMapping("/alterar/{codigo}")
    public ResponseEntity<Aluno> alterar(@RequestBody Aluno aluno, 
                                         @PathVariable("codigo") Long codigo){

        listaAlunos.stream().filter(a -> a.getCodigo() == codigo).forEach(a -> {
            a.setNome(aluno.getNome());
            a.setEndereco(aluno.getEndereco());
        });
        
        return new ResponseEntity(aluno, HttpStatus.OK);
    }

    @PatchMapping("/alterar-endereco/{codigo}")
    public ResponseEntity<Aluno> alterarEndereco(@RequestBody Aluno aluno,
                                         @PathVariable("codigo") Long codigo){

        listaAlunos.stream().filter(a -> a.getCodigo() == codigo).forEach(a -> {
            a.setEndereco(aluno.getEndereco());
        });

        return new ResponseEntity(aluno, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity excluir(@PathVariable("codigo") Long codigo){
        listaAlunos.stream().filter(a -> a.getCodigo() == codigo).forEach(a -> {
            listaAlunos.remove(a);
        });
        return new ResponseEntity("Aluno excluído com sucesso", HttpStatus.OK);
    }



}
