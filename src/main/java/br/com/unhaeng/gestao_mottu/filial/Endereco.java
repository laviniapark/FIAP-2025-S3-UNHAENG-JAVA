package br.com.unhaeng.gestao_mottu.filial;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.*;

@Embeddable
public class Endereco {

    @NotBlank
    @Size(min = 8, max = 8, message = "")
    private String cep;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String numero;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotBlank
    @Size(min=2, max=2, message="")
    private String uf;

    @NotBlank
    private String pais;
}
