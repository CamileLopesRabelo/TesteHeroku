package com.dbc.pessoaapi.entity;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContatoEntity {

    private Integer idContato;
    private Integer idPessoa;
    @NotNull
    private TipoContato tipoContato;
    @NotEmpty
    @NotNull
    @Size(max = 13, message = "numero maior que 13 digitos")
    private String numero;
    @NotEmpty
    @NotNull
    private String descricao;

}
