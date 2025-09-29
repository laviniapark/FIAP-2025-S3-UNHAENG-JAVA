package br.com.unhaeng.gestao_mottu.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.*;
import lombok.Data;

@Embeddable
@Data
public class Endereco {

    @NotBlank(message = "{endereco.cep.notblank}")
    @Size(min = 8, max = 8, message = "{endereco.cep.size}")
    private String cep;

    @NotBlank(message = "{endereco.logradouro.notblank}")
    private String logradouro;

    @NotBlank(message = "{endereco.numero.notblank}")
    private String numero;

    private String complemento;

    @NotBlank(message = "{endereco.cidade.notblank}")
    private String cidade;

    @NotBlank(message = "{endereco.uf.notblank}")
    @Size(min=2, max=2, message="{endereco.uf.size}")
    private String uf;

    @NotBlank(message = "{endereco.pais.notblank}")
    private String pais;
}
