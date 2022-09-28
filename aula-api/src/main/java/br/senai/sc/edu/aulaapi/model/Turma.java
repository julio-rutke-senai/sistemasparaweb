package br.senai.sc.edu.aulaapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Turma {

    @Id
    Long codigo;
    String nome;
    int ano;
}
