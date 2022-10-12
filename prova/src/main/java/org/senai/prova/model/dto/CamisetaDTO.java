package org.senai.prova.model.dto;

import lombok.Data;
import org.senai.prova.model.Categoria;

@Data
public class CamisetaDTO {

    private Long codigo;
    private String modelo;
    private String cor;
    private String descricao;
    private Float custo;
    private Categoria categoria;
    private boolean disponibilidade;

}
