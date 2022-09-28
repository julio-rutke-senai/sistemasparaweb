package br.senai.sc.edu.aulaapi.repository.dto;

import lombok.Data;

@Data
public class AlunoContatoDTO {
    Long codigo;
    String nome;
    String telefone;
    String email;
}
