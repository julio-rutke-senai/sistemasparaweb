package br.senai.sc.edu.aulaapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nome;
    private String endereco;
    private LocalDate nascimento;
    private String cpf;
    private String telefone;
    private String email;

}
