package org.senai.prova.service;

import org.senai.prova.model.Camiseta;
import org.senai.prova.model.dto.CamisetaDTO;
import org.senai.prova.model.dto.CamisetaDisponivelDTO;
import org.senai.prova.repository.CamisetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CamisetaService {

    private final CamisetaRepository repository;

    public CamisetaService(CamisetaRepository repository) {
        this.repository = repository;
    }

    public void cadastrarCamiseta(CamisetaDTO dto){
        Camiseta camiseta = new Camiseta();
        camiseta.setCategoria(dto.getCategoria());
        camiseta.setCor(dto.getCor());
        camiseta.setCusto(dto.getCusto());
        camiseta.setDescricao(dto.getDescricao());
        camiseta.setModelo(dto.getModelo());
        camiseta.setDisponibilidade(dto.isDisponibilidade());

        repository.save(camiseta);
    }

    public List<CamisetaDTO> listarCamisetas(){
        List<Camiseta> camisetas = repository.findAll();
        return camisetas.stream().map(dto -> {
            CamisetaDTO camiseta = new CamisetaDTO();
            camiseta.setCodigo(dto.getCodigo());
            camiseta.setCategoria(dto.getCategoria());
            camiseta.setCor(dto.getCor());
            camiseta.setCusto(dto.getCusto());
            camiseta.setDescricao(dto.getDescricao());
            camiseta.setModelo(dto.getModelo());
            camiseta.setDisponibilidade(dto.isDisponibilidade());
            return camiseta;
        }).collect(Collectors.toList());
    }

    public String atualizarDisponibilidade(CamisetaDisponivelDTO dto, Long codigo){
        Optional<Camiseta> camiseta = repository.findById(codigo);
        if(camiseta.isPresent()){
            camiseta.get().setDisponibilidade(dto.isDisponibilidade());
            repository.save(camiseta.get());
            return "Camiseta alterada com sucesso!";
        }

        return "Camiseta não encontrada!";
    }

}
