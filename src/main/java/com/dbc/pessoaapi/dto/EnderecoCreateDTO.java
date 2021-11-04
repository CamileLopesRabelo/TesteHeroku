package com.dbc.pessoaapi.dto;

import com.dbc.pessoaapi.entity.TipoEndereco;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class EnderecoCreateDTO {
    @ApiModelProperty(value = "id pessoa")
    private Integer idPessoa;
    @NotNull
    @ApiModelProperty(value = "Tipo de endereço")
    private TipoEndereco tipo;
    @NotEmpty
    @Size(max = 250)
    @ApiModelProperty(value = "Logradouro")
    private String logradouro;
    @NotNull
    @ApiModelProperty(value = "número")
    private Integer numero;
    @ApiModelProperty(value = "complemento")
    private String complemento;
    @NotEmpty
    @NotNull
    @Size(max = 8,min = 8,message = "mais de 8 caracteres")
    @ApiModelProperty(value = "cep")
    private String cep;
    @NotEmpty
    @NotNull
    @Size(max = 250)
    @ApiModelProperty(value = "Nome da cidade")
    private String cidade;
    @NotEmpty
    @NotNull
    @ApiModelProperty(value = "Nome do estado")
    private String estado;
    @NotEmpty
    @NotNull
    @ApiModelProperty(value = "Nome do país")
    private String pais;
}
