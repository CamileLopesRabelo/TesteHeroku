package com.dbc.pessoaapi.dto;

import com.dbc.pessoaapi.entity.TipoEndereco;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class EnderecoDTO extends EnderecoCreateDTO{
    @ApiModelProperty(value = "id endereço")
    private Integer idEndereco;
}
