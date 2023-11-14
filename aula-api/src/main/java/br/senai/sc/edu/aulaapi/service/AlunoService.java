package br.senai.sc.edu.aulaapi.service;

import br.senai.sc.edu.aulaapi.model.Aluno;
import br.senai.sc.edu.aulaapi.repository.AlunoRepository;
import br.senai.sc.edu.aulaapi.repository.dto.AlunoContatoDTO;
import br.senai.sc.edu.aulaapi.repository.dto.NovoAlunoDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Long salvar(NovoAlunoDTO novoAlunoDTO){
        Aluno aluno = new Aluno();
        aluno.setNome(novoAlunoDTO.getNome());
        aluno.setCpf(novoAlunoDTO.getCpf());
        aluno.setTelefone(novoAlunoDTO.getTelefone());
        aluno.setNascimento(novoAlunoDTO.getNascimento());
        aluno.setEmail(novoAlunoDTO.getEmail());
        aluno.setEndereco(novoAlunoDTO.getEndereco());
        aluno = alunoRepository.save(aluno);
        return aluno.getCodigo();
    }

    public List<AlunoContatoDTO> listarContatos(){
        List<Aluno> alunos = alunoRepository.findAll();
        List<AlunoContatoDTO> alunoContatoDTOStream = alunos.stream().map(aluno -> {
            AlunoContatoDTO contatoDTO = new AlunoContatoDTO();
            contatoDTO.setCodigo(aluno.getCodigo());
            contatoDTO.setEmail(aluno.getEmail());
            contatoDTO.setTelefone(aluno.getTelefone());
            return contatoDTO;
        }).collect(Collectors.toList());
        return alunoContatoDTOStream;
    }

    public AlunoRepository getRepo(){
        return alunoRepository;
    }



}
