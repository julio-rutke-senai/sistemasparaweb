package br.senai.sc.edu.aulaapi.controller;

import br.senai.sc.edu.aulaapi.model.Aluno;
import br.senai.sc.edu.aulaapi.repository.AlunoRepository;
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

    private final AlunoRepository alunoRepository;

    public AlunosController(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String criarAluno(@RequestBody Aluno aluno){
        alunoRepository.save(aluno);
        return "Aluno adicionado com sucesso";
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Aluno>> listarAlunos(){
        return new ResponseEntity<>(alunoRepository.findAll(), HttpStatus.OK);
    }


    @GetMapping("/listaPorNome")
    public ResponseEntity<List<Aluno>> listarAlunosPorNome(@RequestParam("nome") String nome){
        return new ResponseEntity<>(alunoRepository.findByNomeIgnoreCaseContains(nome), HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Aluno> getAluno(@PathVariable("codigo") Long codigo){
        return new ResponseEntity<>(alunoRepository.findById(codigo).get(), HttpStatus.OK);
    }

    @PutMapping("/alterar/{codigo}")
    public ResponseEntity<Aluno> alterar(@RequestBody Aluno aluno,
                                         @PathVariable("codigo") Long codigo){
        Optional<Aluno> alunoOptional = alunoRepository.findById(codigo);
        if (alunoOptional.isPresent()){
            Aluno alunoPersistir = alunoOptional.get();
            alunoPersistir.setNome(aluno.getNome());
            alunoPersistir.setEndereco(aluno.getEndereco());
            alunoRepository.save(alunoPersistir);
        }
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
        alunoRepository.deleteById(codigo);
        return new ResponseEntity("Aluno excluído com sucesso", HttpStatus.OK);
    }



}
