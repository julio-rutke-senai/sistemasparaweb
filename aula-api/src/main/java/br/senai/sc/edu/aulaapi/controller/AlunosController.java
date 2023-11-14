package br.senai.sc.edu.aulaapi.controller;

import br.senai.sc.edu.aulaapi.model.Aluno;
import br.senai.sc.edu.aulaapi.repository.dto.AlunoContatoDTO;
import br.senai.sc.edu.aulaapi.repository.dto.NovoAlunoDTO;
import br.senai.sc.edu.aulaapi.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunosController {

    private final AlunoService alunoService;

    public AlunosController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String criarAluno(@RequestBody NovoAlunoDTO aluno){
        alunoService.salvar(aluno);
        return "Aluno adicionado com sucesso";
    }

    @GetMapping("/lista")
    public ResponseEntity<List<AlunoContatoDTO>> listarAlunos(){
        return new ResponseEntity<>(alunoService.listarContatos(), HttpStatus.OK);
    }


    @GetMapping("/listaPorNome")
    public ResponseEntity<List<Aluno>> listarAlunosPorNome(@RequestParam("nome") String nome){
        return new ResponseEntity<>(alunoService.getRepo().findByNomeIgnoreCaseContains(nome), HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Aluno> getAluno(@PathVariable("codigo") Long codigo){
        return new ResponseEntity<>(alunoService.getRepo().findById(codigo).get(), HttpStatus.OK);
    }

    @PutMapping("/alterar/{codigo}")
    public ResponseEntity<Aluno> alterar(@RequestBody Aluno aluno,
                                         @PathVariable("codigo") Long codigo){
        Optional<Aluno> alunoOptional = alunoService.getRepo().findById(codigo);
        if (alunoOptional.isPresent()){
            Aluno alunoPersistir = alunoOptional.get();
            alunoPersistir.setNome(aluno.getNome());
            alunoPersistir.setEndereco(aluno.getEndereco());
            alunoService.getRepo().save(alunoPersistir);
        }
        return new ResponseEntity(aluno, HttpStatus.OK);
    }

    @PatchMapping("/alterar-endereco/{codigo}")
    public ResponseEntity<Aluno> alterarEndereco(@RequestBody Aluno aluno,
                                         @PathVariable("codigo") Long codigo){

        return new ResponseEntity(aluno, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity excluir(@PathVariable("codigo") Long codigo){
        alunoService.getRepo().deleteById(codigo);
        return new ResponseEntity("Aluno excluído com sucesso", HttpStatus.OK);
    }



}
