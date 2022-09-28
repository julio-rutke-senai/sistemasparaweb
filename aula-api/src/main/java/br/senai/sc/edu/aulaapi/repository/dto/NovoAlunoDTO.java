package br.senai.sc.edu.aulaapi.repository.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NovoAlunoDTO {

    private Long codigo;
    private String nome;
    private String rua;
    private int numero;
    private String complemento;
    private LocalDate nascimento;
    private String cpf;
    private String telefone;
    private String email;

    public String getEndereco(){
        return rua+", "+numero+", "+complemento;
    }

}
